package com.noobs.gazonuz.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;

    @Column( name = "original_file_name" )
    private String originalFileName;
    @Column( name = "file_path" )
    private String filePath;


    @Column( name = "generated_file_name" )
    private String generatedFileName;


    @Column( name = "mime_type" )
    private String mimeType;


    @Column( name = "file_size" )
    private Long fileSize;
    private String extension;
 /*   @OneToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY )
//    @ToString.Exclude
    private User user;
*/

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Pitch pitch;
}
