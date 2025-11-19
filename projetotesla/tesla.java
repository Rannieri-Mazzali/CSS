
(() => {
  const $ = (q, root = document) => root.querySelector(q);
  const $$ = (q, root = document) => Array.from(root.querySelectorAll(q));

  
  const year = document.getElementById("year");
  if (year) year.textContent = new Date().getFullYear();


  $$('.model-card').forEach(card => {
    const transformBtn = card.querySelector('.btn-transform');
    const img = card.querySelector('.car-img');

    if (transformBtn) {
      transformBtn.addEventListener('click', () => {
        card.classList.toggle('transformed');

       
        if (img) {
          img.animate([
            { transform: 'scale(1) rotate(0)' },
            { transform: 'scale(1.05) rotate(1deg)' },
            { transform: 'scale(1) rotate(0)' }
          ], {
            duration: 500,
            easing: 'cubic-bezier(.22,.61,.36,1)'
          });
        }
      });
    }
  });


  const globalBtn = document.getElementById('globalTransform');
  if (globalBtn) {
    globalBtn.addEventListener('click', () => {
      const cards = $$('.model-card');
      const any = cards.some(c => c.classList.contains('transformed'));
      cards.forEach(c => c.classList.toggle('transformed', !any));
    });
  }

 
  const heroCar = document.getElementById('heroCar');
  if (heroCar) {
    heroCar.addEventListener('click', () => {
      heroCar.classList.toggle('transformed');
    });
  }


  $$('.car-img').forEach(img => {
    img.addEventListener('mouseenter', () => {
      img.classList.add('color-shift');
    });
    img.addEventListener('mouseleave', () => {
      img.classList.remove('color-shift');
    });
  });

  
  window.addEventListener('keydown', e => {
    if (e.key.toLowerCase() === 't') {
      const btn = document.getElementById('globalTransform');
      if (btn) btn.click();
    }
  });

})();

