<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <title>LIBRARY</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/resources/img/home-images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/home-style.css" type="text/css" media="all"/>

</head>
<body>
<!-- Header -->
<div id="header" class="shell">
    <div id="logo"><h1><a href="#">BestSeller</a></h1></div>
    <!-- Navigation -->
    <div id="navigation">
        <ul>
            <li><a href="#" class="active">Home</a></li>
            <li><a href="/books/add">Add book</a></li>
            <li><a href="/category/add">Add category</a></li>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Contacts</a></li>
        </ul>
    </div>
    <!-- End Navigation -->
    <div class="cl">&nbsp;</div>
    <!-- Login-details -->
    <div id="login-details">
        <p>Welcome, <a href="#" id="user">Guest</a> .</p>
        <p><a href="#" class="cart"><img src="/resources/img/home-images/cart-icon.png" alt=""/></a>Shopping Cart (0) <a
                href="#"
                class="sum">$0.00</a>
        </p>
    </div>
    <!-- End Login-details -->
</div>
<!-- End Header -->
<!-- Slider -->
<div id="slider">
    <div class="shell">
        <ul>
            <div th:each="book :${books}" th:object="book">
                <li>
                    <div class="image">
                        <img src="/download?filename=${book.getCoverGeneratedFileName()}" width="180" height="300"
                             class="card-img-top" alt="${book.getCoverOriginalFileName()}" loading="lazy"/>
                    </div>
                    <div class="details">
                        <h2>${book()}</h2>
                        <h3>${book.getAuthor()}</h3>
                        <p class="description">${book.getDescription()}</p>
                        <a href="/selectBook?book=${book.getId()}" class="read-more-btn">Read More</a>
                    </div>
                </li>
            </div>
        </ul>
        <div class="nav">
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
        </div>
    </div>
</div>
<!-- End Slider -->
<!-- Main -->
<div id="main" class="shell">
    <!-- Sidebar -->
    <div id="sidebar">
        <ul class="categories">
            <li>
                <h4>Categories</h4>
                <ul>
<%--                    <c:forEach items="${categories}" var="category">--%>
<%--                        <li><a href="#">${category.getName()}</a></li>--%>
<%--                    </c:forEach>--%>
                </ul>
            </li>
        </ul>
    </div>
    <!-- End Sidebar -->
    <!-- Content -->
    <div id="content">
        <!-- Products -->
        <div class="products">
            <h3>Featured Products</h3>
            <ul>
                <c:forEach items="${books}" var="book">
                <li>
                    <div class="product">
                        <a href="#" class="info">
								<span class="holder">
                                 <div class="image">
                                  <img src="/download?filename=${book.getCoverGeneratedFileName()}" width="180"
                                       height="300"
                                       class="card-img-top" alt="${book.getCoverOriginalFileName()}" loading="lazy"/>
                                 </div>
                                    <span class="book-name">${book.getTitle()}</span>
									<span class="author">by ${book.getAuthor()}</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br/>scelerisque dignissim <br/>sollicitudin nisi</span>
								</span>
                        </a>
                        <a href="/selectBook?book=${book.getId()}" class="buy-btn">BUY NOW <span class="price"><span
                                class="low">$</span>22<span
                                class="high">00</span></span></a>
                    </div>
                </li>
                </c:forEach>
                <!-- End Products -->
        </div>
        <div class="cl">&nbsp;</div>
        <!-- Best-sellers -->
        <div id="best-sellers">
            <h3>Best Sellers</h3>
            <ul>
                <c:forEach items="${books}" var="book">
                    <li>
                        <div class="produ   ct">
                            <a href="/selectBook?book=${book.getId()}">
                                <img src="/download?filename=${book.getCoverGeneratedFileName()}"
                                     alt="${book.getCoverOriginalFileName()}" loading="lazy"/>
                                <span class="book-name">${book.getTitle()}</span>
                                <span class="author">by ${book.getAuthor()}</span>
                                <span class="price"><span class="low">$</span>35<span class="high">00</span></span>
                            </a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- End Best-sellers -->
    </div>
    <!-- End Content -->
    <div class="cl">&nbsp;</div>
</div>
<!-- End Main -->
<!-- Footer -->
<div id="footer" class="shell">
    <div class="top">
        <div class="cnt">
            <div class="col about">
                <h4>About BestSellers</h4>
                <p>Nulla porttitor pretium mattis. Mauris lorem massa, ultricies non mattis bibendum, semper ut erat.
                    Morbi vulputate placerat ligula. Fusce <br/>convallis, nisl a pellentesque viverra, ipsum leo
                    sodales sapien, vitae egestas dolor nisl eu tortor. Etiam ut elit vitae nisl tempor tincidunt. Nunc
                    sed elementum est. Phasellus sodales viverra mauris nec dictum. Fusce a leo libero. Cras accumsan
                    enim nec massa semper eu hendrerit nisl faucibus. Sed lectus ligula, consequat eget bibendum eu,
                    consequat nec nisl. In sed consequat elit. Praesent nec iaculis sapien. <br/>Curabitur gravida
                    pretium tincidunt. </p>
            </div>
            <div class="col store">
                <h4>Store</h4>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Special Offers</a></li>
                    <li><a href="#">Log In</a></li>
                    <li><a href="#">Account</a></li>
                    <li><a href="#">Basket</a></li>
                    <li><a href="#">Checkout</a></li>
                </ul>
            </div>
            <div class="col" id="newsletter">
                <h4>Newsletter</h4>
                <p>Lorem ipsum dolor sit amet consectetur. </p>
                <form action="" method="post">
                    <input type="text" class="field" value="Your Name" title="Your Name"/>
                    <input type="text" class="field" value="Email" title="Email"/>
                    <div class="form-buttons"><input type="submit" value="Submit" class="submit-btn"/></div>
                </form>
            </div>
            <div class="cl">&nbsp;</div>
            <div class="copy">
            </div>
        </div>
    </div>
</div>
<!-- End Footer -->


<script type="text/javascript" src="/resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.jcarousel.min.js"></script>
<script type="text/javascript" src="/resources/js/png-fix.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/main.js"></script>
<script type="text/javascript" src="/resources/js/popper.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>