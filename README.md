
# example
練習用


啟動application後 輸入url : http://localhost:8081/index 後會看到以下畫面
![設定0](https://user-images.githubusercontent.com/16308309/114280564-73825980-9a6c-11eb-8a95-92b55f4a6709.jpg)


----

### `目錄結構`

![設定2](https://user-images.githubusercontent.com/16308309/114278819-15517880-9a64-11eb-8a90-c1c83967efa3.jpg)

1. aop: 請參考 https://openhome.cc/Gossip/SpringGossip/AOPConcept.html。我的範例為紀錄Log
2. controller : Http請求的進入點
3. database : JPA 
4. service : 業務邏輯layer
5. templates: view，是html、jsp....等，預設存放的位置。此範例使用的是thymeleaf


### `Application屬性配置`
![設定1](https://user-images.githubusercontent.com/16308309/114278378-184b6980-9a62-11eb-9b37-1733b8da1764.jpg)

1. server.port: 預設為8080，可以自定義port
2. spring.jpa.hibernate.ddl-auto: 資料庫ddl(Data Definition Language)，有create, update, create-drop, validate, none。範例使用的為update。update為每次啟動application時會依照entity的設定更新資料庫schema
3. spring.h2.console.enabled: 設為true可以使用web console進行操作
4. spring.datasource.url: 連到資料庫的URL
5. spring.datasource.driverClassName: 資料庫JDBC驅動程式 
6. spring.datasource.username: 資料庫帳號
7. spring.datasource.password: 資料庫密碼
8. spring.jpa.database-platform: 設定JPA要使用哪一種資料庫


### `h2 web console`
URL: http://localhost:8081/h2-console

畫面:
![設定3](https://user-images.githubusercontent.com/16308309/114279973-c1e22900-9a69-11eb-97c3-0585dabe006a.jpg)

畫面中Driver Class、JDBC URL、帳號密碼等: 會是前面application.properties所設定的值

登入後:
![設定4](https://user-images.githubusercontent.com/16308309/114280190-b9d6b900-9a6a-11eb-8261-d240255364f0.jpg)

----
### `程式`
程式流程為: Controller => Service => RepositoryFactory => Repository

1. AOP: 範例為求請開始與結束會記錄Log

``` java
	@Pointcut("execution(* com.example.demo.controller..*(..))")
	public void pointcut() {
	}
  ```
* `Poincut`: 指定橫切點為某個com.example.demo.controller這個packge底下任何Controller的所有method


``` java
	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String URI = request.getRequestURI().substring(request.getContextPath().length());
		String methodName = joinPoint.getSignature().getName();

		logger.info("請求開始 : URI = {}, Method = {} ", URI, methodName);
	}
	
		@After("pointcut()")
	public void after(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String URI = request.getRequestURI().substring(request.getContextPath().length());
		String methodName = joinPoint.getSignature().getName();

		logger.info("請求結束 :  URI = {}, Method = {} ", URI, methodName);

	}

```
* `Before`: Before Advice，會在controller的method之前先執行
* `After`: After Advice，會在controller的method執行結束後才執行

2. Transactional 
詳細請看: https://openhome.cc/Gossip/JavaGossip-V2/Transaction.htm

``` java
	@Transactional
	public void addBook(Book book) {
		
		repositoryFactory.getBookRepository().save(book);
			
		throw new RuntimeException("error");
		
	}
```

* `Transactional`: 當使用這個標籤後，若程式發生例外時會將資料rollback回原本的狀態
