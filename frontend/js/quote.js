async function loadQuotes(){
    const res=await fetch('/api/quotes');
    const quotes=await res.json();
    const list=document.getElementById('my-quotes');
    list.innerHTML='';
    quotes.forEach(q=>{
      const li=document.createElement('li');
      li.textContent=`#${q.id} – ${q.status} – $${q.quotePrice??'pending'}`;
      list.appendChild(li);
    });
  }
  
  const form=document.getElementById('quote-form');
  form.addEventListener('submit',async e=>{
    e.preventDefault();
    const formData=new FormData(form);
    await fetch('/api/quotes',{method:'POST',body:formData});
    form.reset();
    loadQuotes();
  });
  
  loadQuotes();