function openseason(nb) {
    var seasons = document.getElementsByClassName("seasons")[0];
    var season = getDirectChildTagNb(seasons, "a", nb-1);
    var episodes = getDirectChildTagNb(seasons, "div", nb-1);
    if (episodes.getAttribute("hidden")==null) {
        closeS(season, episodes);
    } else {
        openS(season, episodes);
    }


    console.log(season);
    console.log(episodes);
}

function openS(season, episodes) {
    season.setAttribute("class", "selected");
    episodes.removeAttribute("hidden");
}

function closeS(season, episodes) {
    season.removeAttribute("class");
    episodes.setAttribute("hidden", "true");
}

function getDirectChildTagNb(node, tag, nb) {
    var count=0;
    for (var i=0 ; i<node.children.length ; i++){
        if (node.children[i].tagName.toUpperCase() == tag.toUpperCase()){
            if (count==nb) {
                return node.children[i];
            }
            count++;
        }
    }
    return null;
}


function checkbox(el) {
    if (el.innerHTML == "check_box_outline_blank") {
        el.innerHTML = "check_box";
    } else {
        el.innerHTML = "check_box_outline_blank";
    }
}