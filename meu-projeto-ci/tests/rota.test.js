const request = require("supertest");
const app = require("../src/app");

test("GET /saudacao deve retornar mensagem", async () => {
  const res = await request(app).get("/saudacao");
  expect(res.statusCode).toBe(200);
  expect(res.body.mensagem).toBeDefined();
});