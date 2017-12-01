(function($){

  "use strict";

  $(window).load(function() {

    // Preloader
    $('.loader').fadeOut();
    $('.loader-mask').delay(350).fadeOut('slow');

    $(window).trigger("resize");
    initTwittslider();
    initOwlCarousel();
    wow.init();

  });


  $(window).resize(function(){
    container_full_height_init();  
    
    var windowWidth = $(window).width();
    if (windowWidth <= 974) {
      $('.dropdown-toggle').attr('data-toggle', 'dropdown');
      $('.navigation, .navigation-overlay').removeClass('sticky');
      $('.local-scroll.desktop-offset-0').localScroll({offset: {top: -60},duration: 1500,easing:'easeInOutExpo'});
      $('.local-scroll.mobile-offset-0').localScroll({offset: {top: 0},duration: 1500,easing:'easeInOutExpo'});
    }
    if (windowWidth > 974) {
      $('.dropdown-toggle').removeAttr('data-toggle', 'dropdown');
      $('.dropdown').removeClass('open');
      $('.local-scroll.mobile-offset-0').localScroll({offset: {top: -60},duration: 1500,easing:'easeInOutExpo'});
      $('.local-scroll.desktop-offset-0').localScroll({offset: {top: 0},duration: 1500,easing:'easeInOutExpo'});
    }

    /* Mobile Navigation 
    -------------------------------------------------------*/
    if (windowWidth > 974 & $('html').hasClass('mobile')) {
      $('.navbar-nav > li.dropdown > a').on('click', function(e){ e.preventDefault(); });
    }

    if (windowWidth <= 974 & $('html').hasClass('mobile')) {
      $('.navbar-nav > li.dropdown > a').unbind('click');
    }

    /* Mobile Menu Resize
    -------------------------------------------------------*/
    $(".navbar .navbar-collapse").css("max-height", $(window).height() - $(".navbar-header").height() );
    
  });


  /* Sticky Navigation
  -------------------------------------------------------*/
  $(window).scroll(function(){

    var windowWidth = $(window).width();    
    if ($(window).scrollTop() > 10 & windowWidth > 974){
      $('header.sticky-nav .navigation').addClass("sticky");
    } else {
      $('header.sticky-nav .navigation').removeClass("sticky");
    }

  });

  /* Onepage Nav
  -------------------------------------------------------*/
  $('#onepage-nav li > a').on('click',function() {
    $(".navbar-collapse").collapse('hide');
  });


  // Smooth Scroll Navigation
  $('.local-scroll').localScroll({offset: {top: -60},duration: 1500,easing:'easeInOutExpo'});
  $('.local-scroll-no-offset').localScroll({offset: {top: 0},duration: 1500,easing:'easeInOutExpo'});


  /* Bootstrap Dropdown Navigation
  -------------------------------------------------------*/
  "use strict";!function(a){"function"==typeof define&&define.amd?define(["jquery"],a):"object"==typeof exports?module.exports=a(require("jquery")):a(jQuery)}(function(a){function b(b){this.$element=a(b),this.$main=this.$element.closest(".dropdown, .dropup, .btn-group"),this.$menu=this.$element.parent(),this.$drop=this.$menu.parent().parent(),this.$menus=this.$menu.siblings(".dropdown-submenu");var d=this.$menu.find("> .dropdown-menu > "+c);this.$submenus=d.filter(".dropdown-submenu"),this.$items=d.not(".dropdown-submenu"),this.init()}var c=":not(.disabled, .divider, .dropdown-header)";return b.prototype={init:function(){this.$element.on({"click.bs.dropdown":this.click.bind(this),keydown:this.keydown.bind(this)}),this.$menu.on("hide.bs.submenu",this.hide.bind(this)),this.$items.on("keydown",this.item_keydown.bind(this)),this.$menu.nextAll(c+":first:not(.dropdown-submenu)").children("a").on("keydown",this.next_keydown.bind(this))},click:function(a){a.stopPropagation(),this.toggle()},toggle:function(){this.$menu.hasClass("open")?this.close():(this.$menu.addClass("open"),this.$menus.trigger("hide.bs.submenu"))},hide:function(a){a.stopPropagation(),this.close()},close:function(){this.$menu.removeClass("open"),this.$submenus.trigger("hide.bs.submenu")},keydown:function(a){if(/^(32|38|40)$/.test(a.keyCode)&&a.preventDefault(),/^(13|32)$/.test(a.keyCode))this.toggle();else if(/^(27|38|40)$/.test(a.keyCode))if(a.stopPropagation(),27==a.keyCode)this.$menu.hasClass("open")?this.close():(this.$menus.trigger("hide.bs.submenu"),this.$drop.removeClass("open").children("a").trigger("focus"));else{var b=this.$main.find("li:not(.disabled):visible > a"),c=b.index(a.target);if(38==a.keyCode&&0!==c)c--;else{if(40!=a.keyCode||c===b.length-1)return;c++}b.eq(c).trigger("focus")}},item_keydown:function(a){27==a.keyCode&&(a.stopPropagation(),this.close(),this.$element.trigger("focus"))},next_keydown:function(a){if(38==a.keyCode){a.preventDefault(),a.stopPropagation();var b=this.$drop.find("li:not(.disabled):visible > a"),c=b.index(a.target);b.eq(c-1).trigger("focus")}}},a.fn.submenupicker=function(c){var d=this instanceof a?this:a(c);return d.each(function(){var c=a.data(this,"bs.submenu");c||(c=new b(this),a.data(this,"bs.submenu",c))})}});
  $('.dropdown-submenu > i').submenupicker();


  /* Search
  -------------------------------------------------------*/
  $('.search-trigger').on('click',function(e){
    e.preventDefault();
    $('.search-wrap').animate({opacity: 'toggle'},500);
    $('.nav-search, #search-close').addClass("open");
    $('.search-wrap .form-control').focus();

  });

  $('.search-close').on('click',function(e){
    e.preventDefault();
    $('.search-wrap').animate({opacity: 'toggle'},500);
    $('.nav-search, #search-close').removeClass("open");

  });

  function closeSearch(){
    $('.search-wrap').fadeOut(200);
    $('.nav-search, #search-close').removeClass("open");
  }
    
  $(document.body).on('click',function(e) {
    closeSearch();
  });

  $(".search-wrap, .search-trigger").on('click',function(e) {
    e.stopPropagation();
  });



  /* Mobile Detect
  -------------------------------------------------------*/
  if (/Android|iPhone|iPad|iPod|BlackBerry|Windows Phone/i.test(navigator.userAgent || navigator.vendor || window.opera)) {
     $("html").addClass("mobile");
     $('.dropdown-toggle').attr('data-toggle', 'dropdown');
  }
  else {
    $("html").removeClass("mobile");
  }

  /* IE Detect
  -------------------------------------------------------*/
  if(Function('/*@cc_on return document.documentMode===10@*/')()){ $("html").addClass("ie"); }


  /* Twitter Slider
  -------------------------------------------------------*/
  function initTwittslider(){
    $('.twitter-slider #tweets ul').addClass('owl-carousel owl-theme text-center').attr('id', 'owl-single');
  }


  /* Owl Carousel
  -------------------------------------------------------*/

  function initOwlCarousel(){
    (function($){
      "use strict";

      /* Single Image
      -------------------------------------------------------*/

      $("#owl-single").owlCarousel({     
        navigation: true,
        pagination: true,
        slideSpeed: 300,
        paginationSpeed: 400,
        singleItem: true,
        navigationText: ["<i class='lnr lnr-chevron-left'></i>", "<i class='lnr lnr-chevron-right'></i>"]
      })

    })(jQuery);
  };


  /* Blog Masonry / FlexSlider
  -------------------------------------------------------*/

   
  // Flexslider / Masonry
  $('#flexslider').flexslider({
    animation: "fade",
    controlNav: false,
    directionNav: true,
    touch: true,
    slideshow: false,
    prevText: ["<i class='fa fa-angle-left'></i>"],
    nextText: ["<i class='fa fa-angle-right'></i>"]
  });


  // Hero Flexslider
  $('#flexslider-hero').flexslider({
    animation: "fade",
    controlNav: false,
    directionNav: true,
    touch: true,
    slideshow: false,
    prevText: ["<i class='fa fa-angle-left'></i>"],
    nextText: ["<i class='fa fa-angle-right'></i>"]
  });



  /* Flickity Slider
  -------------------------------------------------------*/

  // main Slider
  $('#main-slider').flickity({
    cellAlign: 'left',
    contain: true,
    wrapAround: true,
    autoPlay: false,
    prevNextButtons: true,
    percentPosition: true,
    imagesLoaded: true,
    lazyLoad: 1,
    pageDots: false,
    selectedAttraction : 0.1,
    friction: 0.6,
    rightToLeft: false,
    arrowShape: 'M 30,50 L 55,75 L 60,70 L 40,50  L 60,30 L 55,25 Z'
  });

  
  /* Accordion
  -------------------------------------------------------*/
  function toggleChevron(e) {
    $(e.target)
      .prev('.panel-heading')
      .find("a")
      .toggleClass('plus minus');
  }
  $('#accordion').on('hide.bs.collapse', toggleChevron);
  $('#accordion').on('show.bs.collapse', toggleChevron);


  /* Toggle
  -------------------------------------------------------*/
  var allToggles = $(".toggle > .panel-content").hide();
  
  $(".toggle > .acc-panel > a").on('click', function(){

    if ($(this).hasClass("active")) {
      $(this).parent().next().slideUp("easeOutExpo");
      $(this).removeClass("active");
    }

    else {
      $(this).parent().next(".panel-content");
      $(this).addClass("active");
      $(this).parent().next().slideDown("easeOutExpo");
    }
    
    return false;       
  });


  /* Tooltip
  -------------------------------------------------------*/
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })


  // Wow Animations
  var wow = new WOW({
    offset: 50,
    mobile: false
  });

  

  /* FitVIds
  -------------------------------------------------------*/
  $(".video-wrap").fitVids();


  /* Contact Form
  -------------------------------------------------------*/

  var submitContact = $('#submit-message'),
    message = $('#msg');

  submitContact.on('click', function(e){
    e.preventDefault();

    var $this = $(this);
    
    $.ajax({
      type: "POST",
      url: 'contact.php',
      dataType: 'json',
      cache: false,
      data: $('#contact-form').serialize(),
      success: function(data) {

        if(data.info !== 'error'){
          $this.parents('form').find('input[type=text],input[type=email],textarea,select').filter(':visible').val('');
          message.hide().removeClass('success').removeClass('error').addClass('success').html(data.msg).fadeIn('slow').delay(5000).fadeOut('slow');
        } else {
          message.hide().removeClass('success').removeClass('error').addClass('error').html(data.msg).fadeIn('slow').delay(5000).fadeOut('slow');
        }
      }
    });
  });


})(jQuery);


/* Scroll to Top
-------------------------------------------------------*/

(function() {
  "use strict";

  var docElem = document.documentElement,
    didScroll = false,
    changeHeaderOn = 550;
    document.querySelector( '#back-to-top' );
  function init() {
    window.addEventListener( 'scroll', function() {
      if( !didScroll ) {
        didScroll = true;
        setTimeout( scrollPage, 50 );
      }
    }, false );
  }
  
})();

$(window).scroll(function(event){
  var scroll = $(window).scrollTop();
  if (scroll >= 50) {
    $("#back-to-top").addClass("show");
  } else {
    $("#back-to-top").removeClass("show");
  }

});

$('a[href="#top"]').on('click',function(){
  $('html, body').animate({scrollTop: 0}, 1350, "easeInOutQuint");
  return false;
});


/* Full Height Container
-------------------------------------------------------*/

function container_full_height_init(){
  (function($){
    $(".container-full-height").height($(window).height());
  })(jQuery);
}


// /* Style Switcher
// -------------------------------------------------------*/

// $(".main-wrapper").after('<div id="customizer" class="s-close"><span class="corner"><i class="fa fa-cog"></i></span><div id="options" class="text-center"><a href="https://themeforest.net/user/deothemes?ref=DeoThemes" class="btn btn-md btn-fill btn-color mt-40 mb-40"><span>Purchase Now</span></a><h6 class="uppercase">Select Demo</h6><ul class="demo-list clearfix"><li><a href="index.html" target="_blank"><img src="img/demos/agency.jpg" alt=""></a></li><li><a href="index-2.html" target="_blank"><img src="img/demos/business.jpg" alt=""></a></li><li><a href="index-3.html" target="_blank"><img src="img/demos/onepage.jpg" alt=""></a></li><li><a href="index-4.html" target="_blank"><img src="img/demos/classic.jpg" alt=""></a></li><li><a href="index-5.html" target="_blank"><img src="img/demos/portfolio.jpg" alt=""></a></li><li><a href="index-restaurant.html" target="_blank"><img src="img/demos/restaurant.jpg" alt=""></a></li><li><a href="index-restaurant-op.html" target="_blank"><img src="img/demos/restaurant-op.jpg" alt=""></a></li></ul></div></div>');

// $(".corner").on('click',function (){
//   $("#customizer").toggleClass("s-open");
// });