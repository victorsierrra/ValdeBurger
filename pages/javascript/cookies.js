function acceptCookies() {
    // Oculta el pop-up de cookies
    var popup = document.getElementById('cookieConsentPopup');
    popup.style.display = 'none';
  }
  
  function rejectCookies() {
    // Oculta el pop-up de cookies
    var popup = document.getElementById('cookieConsentPopup');
    popup.style.display = 'none';
    // Muestra un mensaje de advertencia al usuario
    alert('Cookies decline. Could affect the experience on the site.');
  }
  
  window.onload = function() {
    // Muestra siempre el pop-up de cookies
    document.getElementById('cookieConsentPopup').style.display = 'block';
  }
  
  function toggleCookieDetails() {
    var boton = document.getElementById('btn-detalle');
    var details = document.getElementById('cookieDetails');
    if (details.style.display === 'none') {
        boton.innerText = 'Less information';
      details.style.display = 'block';
      
    } else {
      details.style.display = 'none';
      boton.innerText = 'More information'
    }
  }
    