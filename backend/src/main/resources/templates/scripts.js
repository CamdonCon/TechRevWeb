// MENU TOGGLE
const menuBtn = document.getElementById('menuBtn');
const navbar = document.getElementById('navbar');

menuBtn.addEventListener('click', () => {
  menuBtn.classList.toggle('open');
  navbar.classList.toggle('open');
});

// BRAND DROPDOWN OPTIONS
const brandOptions = {
  Laptop: ["Apple", "Dell", "HP", "Lenovo", "Asus", "Acer", "Razer", "Microsoft", "Other"],
  Desktop: ["Dell", "HP", "Lenovo", "Asus", "Acer", "Other"],
  Phone: ["Apple", "Samsung", "Google", "OnePlus", "Other"],
  Tablet: ["Apple", "Samsung", "Microsoft", "Other"],
  Monitor: ["Dell", "LG", "Samsung", "Asus", "Other"],
  "Gaming Console": ["Nintendo", "Sony", "Xbox", "Other"],
  Controller: ["Nintendo", "Sony", "Xbox", "Other"],
  Other: ["Other"]
};

function updateBrands() {
  const deviceType = document.getElementById("deviceType").value;
  const brandSelect = document.getElementById("brand");
  brandSelect.innerHTML = '<option value="">-- Select Brand --</option>';

  if (deviceType && brandOptions[deviceType]) {
    brandOptions[deviceType].forEach(brand => {
      const option = document.createElement("option");
      option.value = brand;
      option.textContent = brand;
      brandSelect.appendChild(option);
    });
  }
}

// INTERSECTION OBSERVER FOR ANIMATIONS
const observer = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add('visible');
    } else {
      entry.target.classList.remove('visible');
    }
  });
}, { threshold: 0.5 });

document.querySelectorAll('.service-text, .quote-form, .product-text, .product-card')
  .forEach(el => observer.observe(el));

// HIGHLIGHT NAV LINKS ON SCROLL
const navLinks = document.querySelectorAll('nav a');
window.addEventListener('scroll', () => {
  let current = '';
  document.querySelectorAll('section').forEach(section => {
    const sectionTop = section.offsetTop - 100;
    if (window.scrollY >= sectionTop) {
      current = section.getAttribute('id');
    }
  });

  navLinks.forEach(link => {
    link.classList.remove('active');
    if (link.getAttribute('href') === '#' + current) {
      link.classList.add('active');
    }
  });
});