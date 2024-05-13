window.addEventListener('DOMContentLoaded', (event) =>{
    console.log('DOMContentLoaded')
    const iconoMenu = document.querySelector('.nav-movil');
    const enlacesNav = document.querySelector('.nav-links');
    const body= document.body;

    //toggle = boolean
        iconoMenu.addEventListener('click', () => {
            enlacesNav.classList.toggle('active');
            
            // Bloquea o desbloquea el desplazamiento del cuerpo
            if (enlacesNav.classList.contains('active'))
            {
                body.style.overflow = 'hidden';
            }
            else 
            {
                body.style.overflow = 'auto';
            }
        });
    })