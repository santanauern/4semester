function capitalizar(texto) {
  return texto.charAt(0).toUpperCase() + texto.slice(1);
}

document.getElementById("resultado").innerText = capitalizar("ci funcionando!");

export { capitalizar };