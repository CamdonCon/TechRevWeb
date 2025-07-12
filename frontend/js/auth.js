const form=document.getElementById('reg-form');
form.addEventListener('submit',async e=>{
  e.preventDefault();
  const body={username:user.value,passwordHash:pass.value};
  await fetch('/api/auth/register',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(body)});
  alert('Registered! Now login.');
  window.location='/login.html';
});