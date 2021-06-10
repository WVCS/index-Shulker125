function topFunction() {
  
  document.body.scrollTop = 0;
 document.documentElement.scrollTop = 0;
  
}

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
   var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
    if (width > 540){
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    
  document.getElementById("one").style.height = "110px";
  document.getElementById("two").style.width = "7vw";
  }
  else {
  document.getElementById("one").style.height = "200px";
  document.getElementById("two").style.width = "12vw";
    }
   }
   else {
     return false;
   }
  
}
