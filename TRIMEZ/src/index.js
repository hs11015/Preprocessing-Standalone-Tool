/* 맨 처음에 뜰 값 */

let targetseq = "";
let targetHTML = ``;
let nowstart, nowend;
let nowfontsize = 30;
let mouseX, mouseY;

/*if 100~170bp, 530~1330bp exon
일단 임의로 받아놓음. java standalone에서 받아온 data와 연동해야함*/
const exonLoc = [[100,170], [530,1330]];

function exonLocfunc() {
  
  html = ``

  for (x=0; x<exonLoc.length; x++) {
    html += `<p>${x+1} : ${exonLoc[x][0]} - ${exonLoc[x][1]}</p>`
  }

  html += `
    <p>parameter input 1 : <input type="number" step="1"></p>
    <p>parameter input 2 : <input type="number" step="1"></p><label for="toggle_button1"
    class="close_button">X</label>
    `
  
  document.getElementById("toggle_contents1").innerHTML = html
}


/*if coordinate 정보
일단 임의로 받아놓음. java standalone에서 받아온 data와 연동해야함*/
const coordinateLoc = [[50,170], [300,530], [611,741],[810,1163],[1489,1900]];

function coordinateLocfunc() {

  html = ``

  for (x=0; x<coordinateLoc.length; x++) {
    html += `<p>${x+1} : ${coordinateLoc[x][0]} - ${coordinateLoc[x][1]}</p>`
  }
  
  html += `
    <p>parameter input 1 : <input type="number" step="1"></p>
    <p>parameter input 2 : <input type="number" step="1"></p><label for="toggle_button2"
    class="close_button">X</label>
    `

  document.getElementById("toggle_contents2").innerHTML = html

}

document.addEventListener("mousemove", (e) => {
  mouseX = e.clientX;
  mouseY = e.clientY;

  if (mouseX>=1640 && mouseX<=1845 && mouseY>=80 && mouseY<=875) {
    if (document.querySelector('.sidebar').classList.contains("hidden")){
      sidebarVisibility();
    }
  }
  else {
    if (document.querySelector('.sidebar').classList.contains("hidden")===false){
      sidebarVisibility();
    }
  }
})

function zoomIn() {
  nowfontsize ++;
  if (document.getElementById("feature1")!==null) {
    feature1();
  }
  else if (document.getElementById("feature2")!==null) {
    feature2();
  }
  else if (document.getElementById("feature3")!==null) {
    feature3();
  }
  else if (document.getElementById("target")!==null) {
    targetgene();
  }
}

function zoomOut() {
  nowfontsize --;
  if (document.getElementById("feature1")!==null) {
    feature1();
  }
  else if (document.getElementById("feature2")!==null) {
    feature2();
  }
  else if (document.getElementById("feature3")!==null) {
    feature3();
  }
  else if (document.getElementById("target")!==null) {
    targetgene();
  }
}


/* target gene 일단 출력 */
function targetgene() {
  document.getElementById("app").innerHTML = `
    <h1 id="target" style="font-size: ${nowfontsize}px">${targetseq}</h1>
  `
  targetHTML = document.getElementById("app").innerHTML;
  nowstart=0;
  nowend=document.getElementById("app").innerText.toString().length;
}


/*intron-exon*/
function feature1() {

  html = ``
  
  document.getElementById("app").innerHTML = targetHTML;
  nowseq = document.getElementById("app").innerText.toString();

  x=0;
  for (i=nowstart; i<nowend+1; i++) {
    if (i===exonLoc[x][1] && x<exonLoc.length-1) x += 1;
    if (i >= exonLoc[x][0]-1 && i<exonLoc[x][1]) {
      
      if (nowstart>exonLoc[x][0] && x===0) {
        html += `<h1 id="feature1" style="font-size: ${nowfontsize}px"><span title="exon: ${nowstart}-${exonLoc[x][1]} bp">${targetseq.slice(nowstart, exonLoc[x][1])}</span></h1>`
        i = exonLoc[x][1]-1;
        }
      else if (nowend<exonLoc[x][1] && x<=exonLoc.length-1) {
        html += `<h1 id="feature1" style="font-size: ${nowfontsize}px"><span title="exon: ${exonLoc[x][0]}-${nowend+1} bp">${targetseq.slice(exonLoc[x][0]-1, nowend+1)}</span></h1>`
        i = nowend;
      }
      else{
        html += `<h1 id="feature1" style="font-size: ${nowfontsize}px"><span title="exon: ${exonLoc[x][0]}-${exonLoc[x][1]} bp">${targetseq.slice(exonLoc[x][0]-1, exonLoc[x][1])}</span></h1>`
        i = exonLoc[x][1]-1;
      }
    }

    else {
      html += `<h1 id="target" style="font-size: ${nowfontsize}px"><span title="intron part">${targetseq[i]}</span></h1>`;
    }
  }

  document.getElementById("app").innerHTML = html;
  
  targetHTML = document.getElementById("app").innerHTML;

  /*
  document.getElementById("app").innerHTML = `
    <h1 id="feature1" style="font-size: ${nowfontsize}px">${targetseq}</h1>
  `*/
}

/*coordinate?*/
function feature2() {
  html = ``

  document.getElementById("app").innerHTML = targetHTML;
  nowseq = document.getElementById("app").innerText.toString();
  
  x=0;
  for (i=nowstart; i<nowend+1; i++) {
    if (i===coordinateLoc[x][1] && x<coordinateLoc.length-1) x += 1;
    if (i >= coordinateLoc[x][0]-1 && i<coordinateLoc[x][1]) {
      if (nowstart>coordinateLoc[x][0] && x===0) {
        html += `<h1 id="feature2" style="font-size: ${nowfontsize}px"><span title="selected part: ${nowstart+1}-${coordinateLoc[x][1]} bp">${targetseq.slice(nowstart, coordinateLoc[x][1])}</span></h1>`
        i = coordinateLoc[x][1]-1;
        }
      else if (nowend<coordinateLoc[x][1] && x<=coordinateLoc.length-1) {
        html += `<h1 id="feature2" style="font-size: ${nowfontsize}px"><span title="selected part: ${coordinateLoc[x][0]}-${nowend+1} bp">${targetseq.slice(coordinateLoc[x][0]-1, nowend+1)}</span></h1>`
        i = nowend;
      }
      else{
        html += `<h1 id="feature2" style="font-size: ${nowfontsize}px"><span title="selected part: ${coordinateLoc[x][0]}-${coordinateLoc[x][1]} bp">${targetseq.slice(coordinateLoc[x][0]-1, coordinateLoc[x][1])}</span></h1>`
        i = coordinateLoc[x][1]-1;
      }
    }
    else {
      html += `<h1 id="target" style="font-size: ${nowfontsize}px"><span title="other part">${targetseq[i]}</span></h1>`;
    }
  }

  document.getElementById("app").innerHTML = html;

  targetHTML = document.getElementById("app").innerHTML;

  /*
  document.getElementById("app").innerHTML = `
    <h1 id="feature2" style="font-size: ${nowfontsize}px">${targetseq}</h1>
  `*/
}

function feature3() {
  nowseq = document.getElementById("app").innerText.toString();
  document.getElementById("app").innerHTML = `
    <h1 id="feature3" style="font-size: ${nowfontsize}px">${nowseq}</h1>
  `
  
  targetHTML = document.getElementById("app").innerHTML;
}

function sidebarVisibility() {
  document.querySelector('.sidebar').classList.toggle("hidden");
}

function previewFile() {
  [file] = document.querySelector('input[type=file]').files;
  reader = new FileReader();

  reader.addEventListener("load", () => {
    targetseq = reader.result;
  }, false);

  if (file) {
    reader.readAsText(file);
  }

  targetgene();
}

let highlightseq = ""
let start, end;
function highlighter() {
  start = parseInt(prompt("Enter the starting point","1"));
  end = parseInt(prompt("Enter the End point","1"));
  
  nowseq = document.getElementById("app").innerText.toString();
  highlightseq = targetseq.slice(start-1,end);
  document.getElementById("app").innerHTML = `
    <h1 id="target" style="font-size: ${nowfontsize}px">${targetseq.slice(nowstart-1,start)}</h1>
    <h1 id="highlight" style="font-size: ${nowfontsize}px">${highlightseq}</h1>
    <h1 id="target" style="font-size: ${nowfontsize}px">${targetseq.slice(end-1,nowend)}</h1>
  `
  
  targetHTML = document.getElementById("app").innerHTML;
}

let trimmingseq = "";
function trimming() {
  start = parseInt(prompt("Enter the starting point","1"));
  end = parseInt(prompt("Enter the End point","1"));

  nowseq = document.getElementById("app").innerText.toString();
  trimmingseq = targetseq.slice(start-1+nowstart,end+nowstart);
  document.getElementById("app").innerHTML = `
    <h1 id="target" style="font-size: ${nowfontsize}px">${trimmingseq}</h1>
  `
  
  nowstart = start-1;
  nowend = end-1;

  targetHTML = document.getElementById("app").innerHTML;
  
  if (!confirm("sequence 파일을 output으로 저장하시겠습니까?")){
    alert("다운로드를 하지 않습니다.");
  }
  else {
    alert("다운로드를 진행합니다.");
    download('trimmedOutput.fa', document.getElementById("app").innerText.toString());
    if (!confirm("다운 받은 파일을 딥러닝 모델의 학습데이터로 적용하실 건가요?")){
    
    }else {
      DLEB();
    }
  }
}

function download(filename, text) {
  var pom = document.createElement('a');
  pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
  pom.setAttribute('download', filename);

  if (document.createEvent) {
      var event = document.createEvent('MouseEvents');
      event.initEvent('click', true, true);
      pom.dispatchEvent(event);
  }
  else {
      pom.click();
  }
}

function DLEB() {
  if (!confirm("딥러닝 모델을 쉽게 만들 수 있는 DLEB 사이트로 이동하시겠습니까?")){
    alert("DLEB 연결을 취소합니다.");
  } else {
    const link = 'http://bioinfo.konkuk.ac.kr/DLEB/index.html';
    if (!confirm("현재 창에서 이어서 접속하시려면 확인,\n새 창으로 접속하시려면 취소를 눌러주세요.")){
      alert("새 창에서 DLEB을 연결합니다.");
      window.open(link);
    } else {
      alert("현재 창에서 DLEB을 연결합니다.");
      location.href=link;
    }
  }
}