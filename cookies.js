function acceptCookies() {
    // Oculta el pop-up de cookies
    var popup = document.getElementById('cookieConsentPopup');
    popup.style.display = 'none';
    // Guarda la aceptación de cookies en el almacenamiento local
    localStorage.setItem('cookiesAccepted', 'true');
  }
  
  function rejectCookies() {
    // Oculta el pop-up de cookies
    var popup = document.getElementById('cookieConsentPopup');
    popup.style.display = 'none';
    // Muestra un mensaje de advertencia al usuario
    alert('Cookies no aceptadas. Podría afectar la experiencia en la página.');
  }
  
  window.onload = function() {
    // Muestra el pop-up de cookies si aún no se ha aceptado
    if (!localStorage.getItem('cookiesAccepted')) {
      document.getElementById('cookieConsentPopup').style.display = 'block';
    }
  }
  function toggleCookieDetails() {
    var details = document.getElementById('cookieDetails');
    if (details.style.display === 'none') {
      details.style.display = 'block';
    } else {
      details.style.display = 'none';
    }
  }
  