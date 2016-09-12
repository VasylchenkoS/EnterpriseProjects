<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Theme Made By www.w3schools.com - No Copyright -->
    <title>Beer Bar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            font: 400 15px Lato, sans-serif;
            line-height: 1.8;
            color: #818181;
        }
        h2 {
            font-size: 24px;
            text-transform: uppercase;
            color: #303030;
            font-weight: 600;
            margin-bottom: 30px;
        }
        h4 {
            font-size: 19px;
            line-height: 1.375em;
            color: #303030;
            font-weight: 400;
            margin-bottom: 30px;
        }
        .jumbotron {
            background-color: #f4511e;
            color: #fff;
            padding: 100px 25px;
            font-family: Montserrat, sans-serif;
        }
        .container-fluid {
            padding: 60px 50px;
        }
        .bg-grey {
            background-color: #f6f6f6;
        }
        .logo-small {
            color: #f4511e;
            font-size: 50px;
        }
        .logo {
            color: #f4511e;
            font-size: 200px;
        }
        .img-responsive img {
            max-width: 50%;
            height: auto;
            display: block;
        }
        .carousel-control.right, .carousel-control.left {
            background-image: none;
            color: #f4511e;
        }
        .carousel-indicators li {
            border-color: #f4511e;
        }
        .carousel-indicators li.active {
            background-color: #f4511e;
        }
        .item h4 {
            font-size: 19px;
            line-height: 1.375em;
            font-weight: 400;
            font-style: italic;
            margin: 70px 0;
        }
        .item span {
            font-style: normal;
        }
        .panel {
            border: 1px solid #f4511e;
            border-radius:0 !important;
            transition: box-shadow 0.5s;
        }
        .panel:hover {
            box-shadow: 5px 0px 40px rgba(0,0,0, .2);
        }
        .panel-footer .btn:hover {
            border: 1px solid #f4511e;
            background-color: #fff !important;
            color: #f4511e;
        }
        .panel-heading {
            color: #fff !important;
            background-color: #f4511e !important;
            padding: 25px;
            border-bottom: 1px solid transparent;
            border-top-left-radius: 0px;
            border-top-right-radius: 0px;
            border-bottom-left-radius: 0px;
            border-bottom-right-radius: 0px;
        }
        .panel-footer {
            background-color: white !important;
        }
        .panel-footer h3 {
            font-size: 32px;
        }
        .panel-footer h4 {
            color: #aaa;
            font-size: 14px;
        }
        .panel-footer .btn {
            margin: 15px 0;
            background-color: #f4511e;
            color: #fff;
        }
        .navbar {
            margin-bottom: 0;
            background-color: #f4511e;
            z-index: 9999;
            border: 0;
            font-size: 12px !important;
            line-height: 1.42857143 !important;
            letter-spacing: 4px;
            border-radius: 0;
            font-family: Montserrat, sans-serif;
        }
        .navbar li a, .navbar .navbar-brand {
            color: #fff !important;
        }
        .navbar-nav li a:hover, .navbar-nav li.active a {
            color: #f4511e !important;
            background-color: #fff !important;
        }
        .navbar-default .navbar-toggle {
            border-color: transparent;
            color: #fff !important;
        }
        footer .glyphicon {
            font-size: 20px;
            margin-bottom: 20px;
            color: #f4511e;
        }
        .slideanim {visibility:hidden;}
        .slide {
            animation-name: slide;
            -webkit-animation-name: slide;
            animation-duration: 1s;
            -webkit-animation-duration: 1s;
            visibility: visible;
        }
        .thumbnail {
            padding: 0 0 15px 0;
            border: none;
            border-radius: 0;
        }
        .thumbnail img {
            width: 25%;
            height: 25%;
            margin-bottom: 10px;
        }
        @keyframes slide {
            0% {
                opacity: 0;
                transform: translateY(70%);
            }
            100% {
                opacity: 1;
                transform: translateY(0%);
            }
        }
        @-webkit-keyframes slide {
            0% {
                opacity: 0;
                -webkit-transform: translateY(70%);
            }
            100% {
                opacity: 1;
                -webkit-transform: translateY(0%);
            }
        }
        @media screen and (max-width: 768px) {
            .col-sm-4 {
                text-align: center;
                margin: 15px 0;
            }
            .btn-lg {
                width: 100%;
                margin-bottom: 35px;
            }
        }
        @media screen and (max-width: 480px) {
            .logo {
                font-size: 150px;
            }
        }
    </style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#myPage">Main Page</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#about">ABOUT</a></li>
                <li><a href="#menu">MENU</a></li>
                <li><a href="#plan">BAR PLAN</a></li>
                <li><a href="#employeeContainer">OUR WAITERS</a></li>
                <li><a href="#contact">CONTACT</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron text-center">
    <h1>The Beer Palace</h1>
    <h3>The best places for breakfast, lunch, dinner, light bites and big meals!!!</h3>
        <img src="/images/main-pic.jpg" class="img-rounded" alt="Main pic" width="25%" height="25%">
    <p align="left">CONTACT US</p>
    <p align="left"><span class="glyphicon glyphicon-map-marker"></span> Khoryva St 17, Kyiv, UA</p>
    <p align="left"><span class="glyphicon glyphicon-phone"></span> +038 (044) 435-78-95</p>
    <p align="left"><span class="glyphicon glyphicon-envelope"></span> myrest@organization.com</p>
</div>

<!-- Container (About Section) -->
<div id="about" class="container-fluid bg-grey">
    <div class="row">
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-globe logo slideanim"></span>
        </div>
        <div class="col-sm-8">
            <h2>New Brewery From 2010</h2><br>
            <h3>Newly opened Ber Bar Brewery follows an old tradition of the famous Burgess Brewery, “Die Bürgerliche Brauerei“, called the Burgher by residents. Nice beer was brewed there and the adjacent brewery restaurant was well-known for its domestic cuisine. The new brewery is on three floors and it aims to lure visitors mainly with a special kind of its own beer which it produces in opposition to industrially brewed beer. The Burgess Brewery is equipped with the most modern technology that is currently available for minibreweries.</h3><br>
            <h4><strong>God Bless You</strong>
                “God bless you”, this is how also maltsters greet each other and this greeting is also in the house. “The process of brewing of our beer starts with the selection of the raw ingredients of the highest quality. Brewing is done by a traditional technology of double malting using traditional raw ingredients: malt, water, hops and yeast, nothing else to be added. It is brewed exclusively from hops of the highest quality, sourced from Zatevec region. Water used for production is filtered and adjusted. Our beer is the beer of Pilsner type, it is produced by the standard process of bottom fermentation,” says maltster Peter Pramuk.
                Besides the technology of the highest quality, the process of the beer production is imbedded also in the soul of three owners – beer fans who a few years ago were observing the conceptions of minibreweries with restaurants all around the world. They insisted that beer should be bitter exactly to their taste. And this was something that excellent maltsters – Mr Brauner and Mr Pramuk – have perfectly mastered.</h4><br>
            <h4><strong>Where nice beer is brewed…</strong>
                In order to produce 12 degree beer, the main fermentation takes 8 to 10 days, next fermentation takes 4 weeks. Beer is not filtered or pasteurized, and so it contains live yeast. Beer is carbonized which means that oxidation or beer aging is prevented. Since unfiltered beer does not come into contact with oxygen, rotting is avoided which has a positive impact on its quality. Subsequently, nice hop odour of beer is released and its repletion or greater taste richness is emphasized.</h4><br>
            <h4><strong>There must be a good cuisine.</strong>
                Traditional, conservative, sound and hearty cuisine which goes with beer tries to follow original Pressburg formulas. Naturally, the cuisine is today technologically totally different but some traditional procedures in recipes work till nowadays. The chef Frantisek Sipos stresses the seasonality of some meals or raw food like asparagus, mushrooms, venison or slaughter season which will vary the rigid menu a là carte.
                Also sweet meals like “sufrle” (dumplings) come from Pressburg. Other caloric calibres are meals proved by decades, for example beef sirloin in cream sauce, dill sauce and homemade dumpling, roast knee or deliciously served brewery delicacy or tartar steak and toast.</h4><br>
        </div>
    </div>
</div>

<!-- Container (Menu Section) -->
<div id="menu" class="container-fluid">
    <div class="text-center">
        <h2>Menu</h2>
        <h4>Our best propose for each season</h4>
    </div>
    <div class="row slideanim">
        <c:forEach items="${menus}" var="menu">
            <div class="col-sm-4 col-xs-12">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <h1>${menu.menuName}</h1>
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${menu.dishSet}" var="dish">
                            <p><strong><a href="<c:url value="/dish?dishName=${dish.name}"/>">${dish.name}</a></strong>  ${dish.weight} <h3>$${dish.price}</h3></p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="jumbotron text-center">
    <form class="form-inline" action="/dish">
        <input type="text" id="dishName" name="dishName" class="form-control" size="50" placeholder="Dish Name" required>
        <button type="SUBMIT" class="btn btn-danger" id="dish-find-button"
        onclick="location.href='/#dishName'">Find Dish</button>
    </form>
</div>

<!-- Container (Plan Section) -->
<div id="plan" class="container-fluid text-center">
    <h2>Bar Plan</h2>
    <br>
    <img src="<c:url value="../images/bar-plan.jpg"/>" width="70%" height="70%">
</div>

<!-- Container (Portfolio Section) -->
<div id="employeeContainer" class="container-fluid text-center">
    <h2>Waiters</h2><br>
        <div class="row text-center slideanim">
            <c:forEach items="${employees}" var="waiter">
                <div class="col-sm-3">
                    <div class="thumbnail">
                        <img src="/images/employees/${waiter.surname}.jpg"
                             onerror="if (this.src != '/images/employees/default.jpg') this.src = '/images/employees/default.jpg';"
                             alt="NO IMAGE">
                        <p><strong>${waiter.name}</strong></p>
                        <p>${waiter.position.position}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br>
    <h2>What our customers say</h2>
    <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <h4>"Ресторан хоть и расположен в самом центре столицы , но цены очень даже адекватные . Огромный выбор пива и приятная атмосфера. Про еду ничего не могу сказать, т.к. брал только гренки."<br>
                    <span style="font-style:normal;">Антон</span></h4>
            </div>
            <div class="item">
                <h4>"Достойное заведение для центра. Удобное расположение. Просторный зал, хорошая терасса. Обслуживание понравилось, не навязчивое. Пиво, как и еда вкусное. Рекомендую."<br>
                    <span style="font-style:normal;">Мери</span></h4>
            </div>
            <div class="item">
                <h4>"Интерьер в стиле немецкого пивного ресторана. Мы сидели на веранде. Очень красиво, прохладно, зелено.
                    Вокруг туристы неспешно гуляют, фестиваль Варенья...Официантка была очень занята, но во время подходила и приносила наши заказы быстро.
                    Мы заказали свиную рульку. Конечно это не Мюнхен. Так приготовить и я могу. Хотелось настоящей рульки с хрустящей корочкой.
                    А эта какая-то вареная в томатном соусе. Пива много. Мы специально заказывали разное по 0,33. Хорошее вкусное и разное. Безалкогольные коктейли заказывали детям.
                    Ну это как везде. Дораду заказали и не поняли подачи. Какие-то 2 куска филе. Не так как у них на сайте на картинке.
                    Надо еще сходить в этот ресторан. Много еще сортов пива не попробовали."<br>
                    <span style="font-style:normal;">Кейт</span></h4>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<!-- Container (Contact Section) -->
<div id="contact" class="container-fluid bg-grey">
    <h2 class="text-center">CONTACT</h2>
    <div class="row">
        <div class="col-sm-5">
            <p>If you have any propose, please</p>
            <p>contact us and we'll get back to you within 24 hours.</p>
            <p><span class="glyphicon glyphicon-map-marker"></span> Khoryva St 17, Kyiv, UA</p>
            <p><span class="glyphicon glyphicon-phone"></span> +038 (044) 435-78-95</p>
            <p><span class="glyphicon glyphicon-envelope"></span> myrest@organization.com</p>
        </div>
        <div class="col-sm-7 slideanim">
            <div class="row">
                <div class="col-sm-6 form-group">
                    <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
                </div>
                <div class="col-sm-6 form-group">
                    <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
                </div>
            </div>
            <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea><br>
            <div class="row">
                <div class="col-sm-12 form-group">
                    <button class="btn btn-default pull-right" type="submit" id="sendPurpose">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="googleMap" style="height:400px;width:100%;"></div>

<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
    var myCenter = new google.maps.LatLng(50.466504, 30.514500);

    function initialize() {
        var mapProp = {
            center:myCenter,
            zoom:17,
            scrollwheel:false,
            draggable:false,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
        var marker = new google.maps.Marker({
            position:myCenter,
        });
        marker.setMap(map);
    }
    google.maps.event.addDomListener(window, 'load', initialize);
</script>
<footer class="container-fluid text-center">
    <a href="#myPage" title="To Top">
        <span class="glyphicon glyphicon-chevron-up"></span>
    </a>
    <p>Bootstrap Theme Made By <a href="http://www.w3schools.com" title="Visit w3schools">www.w3schools.com</a></p>
</footer>

<script>
    $(document).ready(function(){
        // Add smooth scrolling to all links in navbar + footer link
        $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
            // Make sure this.hash has a value before overriding default behavior
            if (this.hash !== "") {
                // Prevent default anchor click behavior
                event.preventDefault();

                // Store hash
                var hash = this.hash;

                // Using jQuery's animate() method to add smooth page scroll
                // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
                $('html, body').animate({
                    scrollTop: $(hash).offset().top
                }, 900, function(){

                    // Add hash (#) to URL when done scrolling (default click behavior)
                    window.location.hash = hash;
                });
            } // End if
        });

        $(window).scroll(function() {
            $(".slideanim").each(function(){
                var pos = $(this).offset().top;

                var winTop = $(window).scrollTop();
                if (pos < winTop + 600) {
                    $(this).addClass("slide");
                }
            });
        });
    })
</script>

</body>
</html>


