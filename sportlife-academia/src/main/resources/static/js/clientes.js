const API_URL = '/api/clientes';

document.addEventListener('DOMContentLoaded', () => {
    carregarClientes();

    const form = document.getElementById('formCliente');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const cliente = {
            nome: document.getElementById('nome').value,
            cpf: document.getElementById('cpf').value,
            email: document.getElementById('email').value
        };

        try {
            const resp = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(cliente)
            });

            if (resp.ok) {
                form.reset();
                carregarClientes();
            } else {
                alert('Erro ao salvar cliente');
            }
        } catch (e) {
            alert('Erro ao se comunicar com o servidor');
        }
    });
});

async function carregarClientes() {
    try {
        const resp = await fetch(API_URL);
        const dados = await resp.json();
        const tbody = document.querySelector('#tabelaClientes tbody');
        tbody.innerHTML = '';

        dados.forEach(c => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${c.id}</td>
                <td>${c.nome}</td>
                <td>${c.cpf ?? ''}</td>
                <td>${c.email ?? ''}</td>
                <td>${c.ativo ? 'Sim' : 'Não'}</td>
            `;
            tbody.appendChild(tr);
        });
    } catch (e) {
        console.error(e);
    }
}
