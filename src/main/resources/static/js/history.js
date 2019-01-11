var h = document.getElementsByClassName("history")[0];
var hBar = h.getElementsByClassName("bar")[0];
var lastPoint = h.getElementsByClassName("historyPoint");
lastPoint = lastPoint[lastPoint.length-1];

var height = (lastPoint.offsetTop + lastPoint.offsetHeight/2)-hBar.offsetTop;
hBar.style.height = String(height)+"px";