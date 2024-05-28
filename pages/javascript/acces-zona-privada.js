    const popup = document.getElementById('popup');
    const botoncerrar = document.getElementById('closeBtn');

    popup.style.display = 'block';

    botoncerrar.addEventListener('click', () => {
        popup.style.display = 'none';
    });

