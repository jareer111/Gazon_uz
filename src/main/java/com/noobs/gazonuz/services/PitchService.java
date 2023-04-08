package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.properties.ApplicationProperties;
import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.domains.location.Location;
import com.noobs.gazonuz.dtos.PitchDTO;
import com.noobs.gazonuz.dtos.PitchOrderTimeDTO;
import com.noobs.gazonuz.dtos.upload.DocumentCreateDTO;
import com.noobs.gazonuz.enums.PitchStatus;
import com.noobs.gazonuz.repositories.OrderDAO;
import com.noobs.gazonuz.repositories.PitchPaginationRepository;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PitchService {

    private final PitchRepository pitchRepository;
    private final AddressService addressService;
    private final DocumentService documentService;
    private final OrderDAO orderDAO;
    private final EmailService emailService;
    private final ApplicationProperties properties;
    private final PitchPaginationRepository pitchPaginationRepository;

    public boolean savePitch(PitchDTO dto , User user) {


        ArrayList<Document> docs = new ArrayList<>();
        for ( MultipartFile document : dto.getDocuments() ) {
            Document doc = documentService.createAndGet(new DocumentCreateDTO(document) , user);
            docs.add(doc);
        }

        District district = addressService.getDistrictById(dto.getDistrictId());
        Pitch pitch = Pitch.builder()
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")))
                .fullAddress(dto.getFullAddress())
                .info(dto.getInfo())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .name(dto.getName())
                .price(dto.getPrice())
                .documents(docs)
                .district(district)
                .phoneNumber(dto.getPhoneNumber())
                .status(PitchStatus.INACTIVE)
                .user(user)
                .build();
        pitchRepository.save(pitch);
        return true;
    }

    public List<Pitch> getPitches(String latitude , String longitude) {
        return pitchRepository.findAll();
    }

    public Pitch getPitch(String pitchId) {
        return pitchRepository.getPitch(pitchId);
    }


    public static String daySuffix(String day) {
        int dayInt = Integer.parseInt(day);
        switch ( dayInt % 10 ) {
            case 1:
                if ( dayInt == 11 ) {
                    return "th";
                } else {
                    return "st";
                }
            case 2:
                if ( dayInt == 12 ) {
                    return "th";
                } else {
                    return "nd";
                }
            case 3:
                if ( dayInt == 13 ) {
                    return "th";
                } else {
                    return "rd";
                }
            default:
                return "th";
        }
    }

    public static PitchOrderTimeDTO findDateInterval(long i , long k) {
        String result = "";
        i = i - 1;
        PitchOrderTimeDTO pitchOrderTimeDTO = new PitchOrderTimeDTO();
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Tashkent"));
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime currentDate = LocalDateTime.of(localDate , localTime);
        if ( currentDate.plusDays(k - 1).plusHours(i).isAfter(currentDateTime) ) {
            if ( i < 10 ) {
                result += "0" + i + ":00-";
            } else {
                result += i + ":00-";
            }
            if ( i + 1 >= 10 ) {
                result += i + 1 + ":00";
            } else {
                result += "0" + ( i + 1 ) + ":00";
            }
            pitchOrderTimeDTO.setIsAvl(true);
        } else {
            pitchOrderTimeDTO.setIsAvl(false);
        }
        pitchOrderTimeDTO.setMessage(result);
        return pitchOrderTimeDTO;
    }

    public void updateStatus(String id , PitchStatus status) {


        final Optional<Pitch> pitchOptional = pitchRepository.findById(id);


        pitchOptional.ifPresent(pitch -> {


            final User user = pitch.getUser();
            final String email = user.getEmail();
            String messageBody = null;
            String messageHeader = null;

            if ( user.isEmailNotificationsAllowed() ) {
                switch ( status ) {
                    case BLOCKED -> {
                        messageBody = properties.getProperties().getProperty("pitch.status.blocked.message.body").formatted(pitch.getCreatedAt());
                        messageHeader = properties.getProperties().getProperty("pitch.status.blocked.message.header");
                    }
                    case REJECTED -> {
                        messageBody = properties.getProperties().getProperty("pitch.status.rejected.message.body").formatted(pitch.getCreatedAt());
                        messageHeader = properties.getProperties().getProperty("pitch.status.rejected.message.header");

                    }
                    case ACTIVE -> {
                        messageBody = properties.getProperties().getProperty("pitch.status.active.message.body").formatted(pitch.getCreatedAt());
                        messageHeader = properties.getProperties().getProperty("pitch.status.active.message.header");
                    }
                    case INACTIVE -> {
                        messageBody = properties.getProperties().getProperty("pitch.status.inactive.message.body").formatted(pitch.getCreatedAt());
                        messageHeader = properties.getProperties().getProperty("pitch.status.inactive.message.header");
                    }
                    case REQUESTED -> {
                        messageBody = properties.getProperties().getProperty("pitch.status.requested.message.body").formatted(pitch.getCreatedAt());
                        messageHeader = properties.getProperties().getProperty("pitch.status.requested.message.header");
                    }
                }

                emailService.sendMessageToEmailThroughSMTP(email , messageBody , messageHeader);
            }

            pitchRepository.updateStatusById(status , id);

        });

    }


    public List<Pitch> getSearchedPitches(Location location , int page , int perPage , String search) {
        String searchBy = "%" + search + "%";
        final Pageable pageable = PageRequest.of(page , perPage);
        return pitchPaginationRepository.pitches(searchBy , location.getLatitude() - 0.0015 , location.getLatitude() + 0.0015 , location.getLongitude() - 0.0015 , location.getLongitude() + 0.0015 , pageable);
    }


    public long getSize(Location location , String search) {
        String searchBy = "%" + search + "%";
        return pitchPaginationRepository.pitchesCount(searchBy , location.getLatitude() - 0.0015 , location.getLatitude() + 0.0015 , location.getLongitude() - 0.0015 , location.getLongitude() + 0.0015);
    }


    public Boolean checkBooked(long i , long k , String pitchId) {
        List<Order> orderList = orderDAO.findAllAcceptedOrdersByPitchId(pitchId);
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime currentDate = LocalDateTime.of(localDate , localTime).plusDays(k - 1).plusHours(i - 1);
        for ( Order order : orderList ) {
            if ( order.getStartTime().isBefore(currentDate.plusMinutes(5)) &&
                 order.getStartTime().plusMinutes(order.getMinutes()).isAfter(currentDate) ) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkBookedForValidation(String choiceDate , String duration , String pitchId) {
        List<Order> orderList = orderDAO.findAllAcceptedOrdersByPitchId(pitchId);
        LocalDateTime currentDate = LocalDateTime.parse(choiceDate);
        for ( Order order : orderList ) {
            if ( order.getStartTime().isBefore(currentDate.plusMinutes(5)) &&
                 order.getStartTime().plusMinutes(order.getMinutes()).isAfter(currentDate) ) {
                return true;
            }
            if ( order.getStartTime().isBefore(currentDate.plusMinutes(Long.parseLong(duration) + 5)) &&
                 order.getStartTime().plusMinutes(order.getMinutes()).isAfter(currentDate) ) {
                return true;
            }
        }
        return false;
    }

    public List<Pitch> getPitchesByDistrict(String districtId) {
        return pitchRepository.findByDistrict(addressService.getDistrictById(districtId));
    }

    public boolean updatePitch(PitchDTO dto , User user) {
//        Pitch pitch = pitchRepository.getPitch(dto.getId());
//        Pitch newPitch = pitchDomainFactory(dto , pitch , user);
//        pitchRepository.update(newPitch);
        return true;
    }

    public List<Pitch> findByUserId(String id) {
        return pitchRepository.findByUserId(id);
    }
}
