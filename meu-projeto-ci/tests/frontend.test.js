import { capitalizar } from "../public/script.js";

test("capitalizar deve colocar primeira letra maiúscula", () => {
  expect(capitalizar("teste")).toBe("Teste");
});