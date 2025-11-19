// script.js — copie e cole diretamente
// Interações: transformar individual, transformar todos, atalho 'T', acessibilidade leve.

(() => {
  const $ = (sel, root = document) => root.querySelector(sel);
  const $$ = (sel, root = document) => Array.from(root.querySelectorAll(sel));

  // Atualiza ano no rodapé
  const yearEl = document.getElementById('year');
  if (yearEl) yearEl.textContent = new Date().getFullYear();

  // Preload imagens (melhora UX)
  const preload = [
    'https://digitalassets.tesla.com/tesla-contents/image/upload/f_auto%2Cq_auto/Model-S-New-Specs-Plaid-Desktop-Imperial.png',
    'https://digitalassets.tesla.com/tesla-contents/image/upload/f_auto%2Cq_auto/Model-3-Standard-Specs-Desktop-Imperial.png',
    'https://digitalassets.tesla.com/tesla-contents/image/upload/f_auto%2Cq_auto/Model-X-New-Specs-Plaid-Desktop-Imperial.png'
  ];
  preload.forEach(src => { const i = new Image(); i.src = src; });

  // Individual transform buttons
  $$('.model-card').forEach(card => {
    const btn = card.querySelector('.transform');
    btn && btn.addEventListener('click', () => {
      card.classList.toggle('transformed');
      // feedback visual (Web Animations API)
      const img = card.querySelector('.model-img');
      if (img && img.animate) {
        img.animate(
          [{ transform: 'scale(1)' }, { transform: 'scale(1.03)' }, { transform: 'scale(1)' }],
          { duration: 420, easing: 'ease-out' }
        );
      }
    });
  });

  // Global transform
  const globalBtn = document.getElementById('globalTransform');
  if (globalBtn) {
    globalBtn.addEventListener('click', () => {
      const cards = $$('.model-card');
      const any = cards.some(c => c.classList.contains('transformed'));
      cards.forEach(c => c.classList.toggle('transformed', !any));
      // small collective pulse
      cards.forEach(c => {
        const img = c.querySelector('.model-img');
        if (img && img.animate) {
          img.animate(
            [{ transform: 'translateY(0) scale(1)' }, { transform: 'translateY(-8px) scale(1.02)' }, { transform: 'translateY(0) scale(1)' }],
            { duration: 520, easing: 'cubic-bezier(.2,.9,.2,1)' }
          );
        }
      });
    });
  }

  // Hero image clickable transform (visual only)
  const heroImg = document.getElementById('heroImage');
  if (heroImg) {
    heroImg.addEventListener('click', () => {
      heroImg.classList.toggle('transformed');
      heroImg.animate([{ transform: 'scale(1)' }, { transform: 'scale(1.03)' }, { transform: 'scale(1)' }], { duration: 360, easing: 'ease-out' });
    });
  }

  // Keyboard shortcut: 'T' toggles global
  window.addEventListener('keydown', e => {
    if (e.key && e.key.toLowerCase() === 't') {
      const btn = document.getElementById('globalTransform');
      btn && btn.click();
    }
  });

  // Pointer feedback for buttons
  document.addEventListener('pointerdown', e => {
    const t = e.target.closest('.btn');
    if (t) t.style.transform = 'translateY(1px) scale(0.997)';
  });
  document.addEventListener('pointerup', e => {
    const t = e.target.closest('.btn');
    if (t) t.style.transform = '';
  });

  // Respect reduced-motion
  if (window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
    document.documentElement.classList.add('reduced-motion');
  }
})();
