/*********************************************************************
*  #### Twitter Post Fetcher v16.0.1 ####
*  Coded by Jason Mayes 2015. A present to all the developers out there.
*  www.jasonmayes.com
*  Please keep this disclaimer with my code if you use it. Thanks. :-)
*  Got feedback or questions, ask here:
*  http://www.jasonmayes.com/projects/twitterApi/
*  Github: https://github.com/jasonmayes/Twitter-Post-Fetcher
*  Updates will be posted to this site.
*********************************************************************/
(function(C,p){"function"===typeof define&&define.amd?define([],p):"object"===typeof exports?module.exports=p():p()})(this,function(){function C(a){if(null===r){for(var f=a.length,b=0,k=document.getElementById(D),g="<ul>";b<f;)g+="<li>"+a[b]+"</li>",b++;k.innerHTML=g+"</ul>"}else r(a)}function p(a){return a.replace(/<b[^>]*>(.*?)<\/b>/gi,function(a,b){return b}).replace(/class="(?!(tco-hidden|tco-display|tco-ellipsis))+.*?"|data-query-source=".*?"|dir=".*?"|rel=".*?"/gi,"")}function E(a){a=a.getElementsByTagName("a");
for(var f=a.length-1;0<=f;f--)a[f].setAttribute("target","_blank")}function l(a,f){for(var b=[],k=new RegExp("(^| )"+f+"( |$)"),g=a.getElementsByTagName("*"),h=0,d=g.length;h<d;h++)k.test(g[h].className)&&b.push(g[h]);return b}function F(a){if(void 0!==a&&0<=a.innerHTML.indexOf("data-srcset"))return a=a.innerHTML.match(/data-srcset="([A-z0-9%_\.-]+)/i)[0],decodeURIComponent(a).split('"')[1]}var D="",f=20,G=!0,v=[],x=!1,y=!0,w=!0,z=null,A=!0,B=!0,r=null,H=!0,I=!1,t=!0,J=!0,K=!1,m=null,L={fetch:function(a){void 0===
a.maxTweets&&(a.maxTweets=20);void 0===a.enableLinks&&(a.enableLinks=!0);void 0===a.showUser&&(a.showUser=!0);void 0===a.showTime&&(a.showTime=!0);void 0===a.dateFunction&&(a.dateFunction="default");void 0===a.showRetweet&&(a.showRetweet=!0);void 0===a.customCallback&&(a.customCallback=null);void 0===a.showInteraction&&(a.showInteraction=!0);void 0===a.showImages&&(a.showImages=!1);void 0===a.linksInNewWindow&&(a.linksInNewWindow=!0);void 0===a.showPermalinks&&(a.showPermalinks=!0);void 0===a.dataOnly&&
(a.dataOnly=!1);if(x)v.push(a);else{x=!0;D=a.domId;f=a.maxTweets;G=a.enableLinks;w=a.showUser;y=a.showTime;B=a.showRetweet;z=a.dateFunction;r=a.customCallback;H=a.showInteraction;I=a.showImages;t=a.linksInNewWindow;J=a.showPermalinks;K=a.dataOnly;var l=document.getElementsByTagName("head")[0];null!==m&&l.removeChild(m);m=document.createElement("script");m.type="text/javascript";m.src=void 0!==a.list?"https://syndication.twitter.com/timeline/list?callback=twitterFetcher.callback&dnt=false&list_slug="+
a.list.listSlug+"&screen_name="+a.list.screenName+"&suppress_response_codes=true&lang="+(a.lang||"en")+"&rnd="+Math.random():void 0!==a.profile?"https://syndication.twitter.com/timeline/profile?callback=twitterFetcher.callback&dnt=false&screen_name="+a.profile.screenName+"&suppress_response_codes=true&lang="+(a.lang||"en")+"&rnd="+Math.random():void 0!==a.likes?"https://syndication.twitter.com/timeline/likes?callback=twitterFetcher.callback&dnt=false&screen_name="+a.likes.screenName+"&suppress_response_codes=true&lang="+
(a.lang||"en")+"&rnd="+Math.random():"https://cdn.syndication.twimg.com/widgets/timelines/"+a.id+"?&lang="+(a.lang||"en")+"&callback=twitterFetcher.callback&suppress_response_codes=true&rnd="+Math.random();l.appendChild(m)}},callback:function(a){function m(a){var b=a.getElementsByTagName("img")[0];b.src=b.getAttribute("data-src-2x");return a}var b=document.createElement("div");b.innerHTML=a.body;"undefined"===typeof b.getElementsByClassName&&(A=!1);a=[];var k=[],g=[],h=[],d=[],q=[],n=[],e=0;if(A)for(b=
b.getElementsByClassName("timeline-Tweet");e<b.length;){0<b[e].getElementsByClassName("timeline-Tweet-retweetCredit").length?d.push(!0):d.push(!1);if(!d[e]||d[e]&&B)a.push(b[e].getElementsByClassName("timeline-Tweet-text")[0]),q.push(b[e].getAttribute("data-tweet-id")),k.push(m(b[e].getElementsByClassName("timeline-Tweet-author")[0])),g.push(b[e].getElementsByClassName("dt-updated")[0]),n.push(b[e].getElementsByClassName("timeline-Tweet-timestamp")[0]),void 0!==b[e].getElementsByClassName("timeline-Tweet-media")[0]?
h.push(b[e].getElementsByClassName("timeline-Tweet-media")[0]):h.push(void 0);e++}else for(b=l(b,"timeline-Tweet");e<b.length;){0<l(b[e],"timeline-Tweet-retweetCredit").length?d.push(!0):d.push(!1);if(!d[e]||d[e]&&B)a.push(l(b[e],"timeline-Tweet-text")[0]),q.push(b[e].getAttribute("data-tweet-id")),k.push(m(l(b[e],"timeline-Tweet-author")[0])),g.push(l(b[e],"dt-updated")[0]),n.push(l(b[e],"timeline-Tweet-timestamp")[0]),void 0!==l(b[e],"timeline-Tweet-media")[0]?h.push(l(b[e],"timeline-Tweet-media")[0]):
h.push(void 0);e++}a.length>f&&(a.splice(f,a.length-f),k.splice(f,k.length-f),g.splice(f,g.length-f),d.splice(f,d.length-f),h.splice(f,h.length-f),n.splice(f,n.length-f));var b=[],e=a.length,c=0;if(K)for(;c<e;)b.push({tweet:a[c].innerHTML,author:k[c].innerHTML,time:g[c].textContent,image:F(h[c]),rt:d[c],tid:q[c],permalinkURL:void 0===n[c]?"":n[c].href}),c++;else for(;c<e;){if("string"!==typeof z){var d=g[c].getAttribute("datetime"),u=new Date(g[c].getAttribute("datetime").replace(/-/g,"/").replace("T",
" ").split("+")[0]),d=z(u,d);g[c].setAttribute("aria-label",d);if(a[c].textContent)if(A)g[c].textContent=d;else{var u=document.createElement("p"),r=document.createTextNode(d);u.appendChild(r);u.setAttribute("aria-label",d);g[c]=u}else g[c].textContent=d}d="";G?(t&&(E(a[c]),w&&E(k[c])),w&&(d+='<div class="user">'+p(k[c].innerHTML)+"</div>"),d+='<p class="tweet">'+p(a[c].innerHTML)+"</p>",y&&(d=J?d+('<p class="timePosted"><a href="'+n[c]+'">'+g[c].getAttribute("aria-label")+"</a></p>"):d+('<p class="timePosted">'+
g[c].getAttribute("aria-label")+"</p>"))):(w&&(d+='<p class="user">'+k[c].textContent+"</p>"),d+='<p class="tweet">'+a[c].textContent+"</p>",y&&(d+='<p class="timePosted">'+g[c].textContent+"</p>"));H&&(d+='<p class="interact"><a href="https://twitter.com/intent/tweet?in_reply_to='+q[c]+'" class="twitter_reply_icon"'+(t?' target="_blank">':">")+'Reply</a><a href="https://twitter.com/intent/retweet?tweet_id='+q[c]+'" class="twitter_retweet_icon"'+(t?' target="_blank">':">")+'Retweet</a><a href="https://twitter.com/intent/favorite?tweet_id='+
q[c]+'" class="twitter_fav_icon"'+(t?' target="_blank">':">")+"Favorite</a></p>");I&&void 0!==h[c]&&(d+='<div class="media"><img src="'+F(h[c])+'" alt="Image from tweet" /></div>');b.push(d);c++}C(b);x=!1;0<v.length&&(L.fetch(v[0]),v.splice(0,1))}};return window.twitterFetcher=L});



// Twitter Feed
	var config1 = {
  "id": '594366594521804800',
  "domId": 'tweets',
  "showUser": false,
  "showInteraction": false,
  "showPermalinks": false,
  "maxTweets": 3,
  "enableLinks": true
};
twitterFetcher.fetch(config1);