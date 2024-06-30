# spring-gift-product

## 구현할 기능 목록

- Product.java
  - 상품 정보 클래스
- ProductRequestDto
  - 요청으로 들어오는 데이터에 대한 설계도로 record 사용
- ProductResponseDto
  - 응답으로 보내주는 데이터에 대한 설계도로 record 사용
- ProductController
  - 웹 요청에 대한 처리로직 구현
- ProductDao
  - 데이터베이스에 접근하는 클래스

### 1. 상품 조회
- 모든 상품 조회


    HTTP 메서드 : GET
    url : /api/products
  
    웹에서 요청이 들어오면 컨트롤러는 DAO를 통해 모든 상품을 조회하여 List<Product>에 넣어 반환한다.
- 아이디로 상품 조회
  
  
    HTTP 메서드 : GET
    url : /api/products/{id}

    컨트롤러에서 웹으로부터 받은 id로 DAO를 통해 상품을 조회한다. 조회되지 않은 경우는 예외처리를 하고, 조회한 상품에 대한 정보를 ProductResponseDto에 담아 반환한다.


### 2. 상품 추가

    HTTP 메서드 : POST
    url : /api/products

    컨트롤러는 웹으로부터의 요청을 통해 추가할 상품에 대한 정보인 name, price, url 값을 받는다. 이를 Product 객체를 생성하여 DAO를 통해 insert한다. 

### 3. 상품 수정
  
    HTTP 메서드 : PUT
    url : /api/products/{id}

    컨트롤러에서 수정하고자 하는 상품을 DAO를 통해 조회하고 존재한다면 update한다. 존재하지 않으면 예외처리된다

### 4. 상품 삭제

    HTTP 메서드 : DELETE
    url : /api/products/{id}

    컨트롤러는 웹에서 받은 id로 DAO를 통해 상품을 조회한다. 조회할 수 없으면 예외처리가 되고 조회 성공한 상품에 대해서 delete한다. 
