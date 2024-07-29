# Cardapio API

## API REST para cadastro de produtos e configuração de cardápios

**Projeto em produção:** https://cardapio-api-production-d4a2.up.railway.app/swagger-ui.html


### Destaques
- OpenAPI Documentation com exemplos de dados de entrada e saída
- Tratamento de exceções global
- API não expõe classes do dominio (utiliza models - "DTOs")


### Controllers

**CategoriaController**
- Permite o cadastro e listagem de categorias de produtos

**ProdutoController**
- Permite o cadastro e listagem de produtos
    
**CardapioController**
- Permite o cadastro e listagem de cardápios (ex: cardapios diferentes para o horário do Almoço e Jantar)
- Permite a configuração de cada cardápio associando os produtos desejados (previamente cadastrados) a um cardápio
