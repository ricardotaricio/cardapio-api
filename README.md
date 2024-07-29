# lab-padroes-projeto-spring

## API REST para cadastro de produtos e configuração de cardápios usando padrões de projeto num projeto Spring Boot

### Padrões utilizados
* Facade
* Strategy
* Repository

### Controllers
**ProdutoController**
- Permite o cadastro e listagem de produtos
    
**CardapioController**
- Permite o cadastro e listagem de cardápios (ex: cardapios diferentes para o horário do Almoço e Jantar)
- Permite a configuração de cada cardápio associando os produtos desejados (previamente cadastrados) a um cardápio
