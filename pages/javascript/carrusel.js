let slideIndex = 0;
showSlides();

function showSlides() {
    let Contador;
    let slides = document.getElementsByClassName("slides")[0].getElementsByTagName("img");
    for (Contador = 0; Contador < slides.length; Contador++) {
        slides[Contador].style.display = "none";  
    }
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}    
    slides[slideIndex-1].style.display = "block";
    setTimeout(showSlides, 8000); // Cambia imagen cada 10 segundos
}