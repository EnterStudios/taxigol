
Repositorio fernandohur/taxigol

Hay 3 proyectos

1. restz
2. taxigol-taxi
3. taxigol-test

=======================
======== restz ========
=======================

Wrapper para Apache HttpClient. Es un una forma fácil para hacer requests http
En este momento estan soportados los siguientes requests:
1. GET
2. POST
3. PUT

La forma general para usarlo es

	//Ejemplo como hacer un GET
	Restz r = new DefaultRestz();
	GetMethod get = r.get(<parametros>);
	String content = get.getContent()
	
	//Ejemplo como hacer un POST
	PostMethod post = r.post(<params>);
	String content = post.getContent();

	//Ejemplo como hacer PUT
	PutMethod put = r.put(<params>);
	String content = put.getContent();
	
========================
===== taxigol-taxi =====
========================

Contiene la aplicación del taxista y por ahora la lógica de negocio
Está dividida en:

1. Application
2. Activities
3. Model
4. Controller 
5. Receivers

=== Aplication ===

Es una clase que hace varias cosas
	- Inicia los controladores
	- Inicia Restz que es la forma de comuicarse con el servidor
	- Mantiene el estado de la aplicación
	- extiende Application osea que puede ser accedida por todos los Activities

=== Activities ====

Contiene las Activity de Android de la aplicación
En este momento hay

	- auth
	- confirmacion
	- map
	- panic
	- servicio de taxi detail
	- servicio de taxi panic
	- solicitud confirmada
























