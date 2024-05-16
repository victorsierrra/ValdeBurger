document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the default form submission

    const form = this;  // Referencia al formulario
    const formData = new FormData(form);  // Gather form data
    const formResponse = document.getElementById('formResponse');

    // Dummy API endpoint for demonstration (you can use your actual endpoint here)
    const endpoint = 'https://jsonplaceholder.typicode.com/posts';

    fetch(endpoint, {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);  // Output the response from the server
        formResponse.textContent = 'Mensaje enviado con éxito. Gracias por contactarnos!';
        formResponse.style.color = 'green';
        form.reset();  // Limpia el formulario después de enviar los datos con éxito
    })
    .catch(error => {
        console.error('Error:', error);
        formResponse.textContent = 'Hubo un error al enviar el mensaje. Por favor, intenta de nuevo más tarde.';
        formResponse.style.color = 'red';
    });
});