# UsuarioApiServiceApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**altaRegistroPractica**](UsuarioApiServiceApi.md#altaRegistroPractica) | **PUT** /usuarios/altaPractica/{id} | Registro alta de los usuarios al incorporarse a las prácticas |
| [**borrarRegistroPractica**](UsuarioApiServiceApi.md#borrarRegistroPractica) | **DELETE** /usuarios/borrarRegistro/{id} | Borrar registro alumno |
| [**cambiarContrasena**](UsuarioApiServiceApi.md#cambiarContrasena) | **PUT** /usuarios/password/{id} | Cambiar contraseña de usuarios |
| [**consultarFechas**](UsuarioApiServiceApi.md#consultarFechas) | **GET** /usuarios/consultarFechas | Consultar fechas existentes |
| [**consultarRegistros**](UsuarioApiServiceApi.md#consultarRegistros) | **GET** /usuarios/consultarRegistros/{id} | Consultar registros del usuario |
| [**login**](UsuarioApiServiceApi.md#login) | **GET** /usuarios/login | Loguea al usuario |


<a id="altaRegistroPractica"></a>
# **altaRegistroPractica**
> altaRegistroPractica(id, fechaId, horas, detalle)

Registro alta de los usuarios al incorporarse a las prácticas

Registro alta usuarios

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    Long id = 56L; // Long | 
    Long fechaId = 56L; // Long | 
    Integer horas = 56; // Integer | 
    String detalle = "detalle_example"; // String | 
    try {
      apiInstance.altaRegistroPractica(id, fechaId, horas, detalle);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#altaRegistroPractica");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **fechaId** | **Long**|  | |
| **horas** | **Integer**|  | |
| **detalle** | **String**|  | |

### Return type

null (empty response body)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="borrarRegistroPractica"></a>
# **borrarRegistroPractica**
> borrarRegistroPractica(id, registroId)

Borrar registro alumno

Borrar registro

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    Long id = 56L; // Long | 
    Long registroId = 56L; // Long | 
    try {
      apiInstance.borrarRegistroPractica(id, registroId);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#borrarRegistroPractica");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **registroId** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="cambiarContrasena"></a>
# **cambiarContrasena**
> cambiarContrasena(id, antiguaContrasena, nuevaContrasena)

Cambiar contraseña de usuarios

Cambiar contraseña a un usuario

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    Long id = 56L; // Long | 
    String antiguaContrasena = "antiguaContrasena_example"; // String | 
    String nuevaContrasena = "nuevaContrasena_example"; // String | 
    try {
      apiInstance.cambiarContrasena(id, antiguaContrasena, nuevaContrasena);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#cambiarContrasena");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **antiguaContrasena** | **String**|  | |
| **nuevaContrasena** | **String**|  | |

### Return type

null (empty response body)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarFechas"></a>
# **consultarFechas**
> List&lt;Fecha&gt; consultarFechas()

Consultar fechas existentes

Consultar fechas que existan en la base de datos

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    try {
      List<Fecha> result = apiInstance.consultarFechas();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#consultarFechas");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Fecha&gt;**](Fecha.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarRegistros"></a>
# **consultarRegistros**
> List&lt;RegistroPractica&gt; consultarRegistros(id, fechaDesde, fechaHasta, filtro)

Consultar registros del usuario

Consultar registros del usuario

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    Long id = 56L; // Long | 
    LocalDate fechaDesde = LocalDate.now(); // LocalDate | 
    LocalDate fechaHasta = LocalDate.now(); // LocalDate | 
    String filtro = "filtro_example"; // String | 
    try {
      List<RegistroPractica> result = apiInstance.consultarRegistros(id, fechaDesde, fechaHasta, filtro);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#consultarRegistros");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **fechaDesde** | **LocalDate**|  | |
| **fechaHasta** | **LocalDate**|  | |
| **filtro** | **String**|  | |

### Return type

[**List&lt;RegistroPractica&gt;**](RegistroPractica.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="login"></a>
# **login**
> Usuario login(nombreUsuario, password)

Loguea al usuario

Loguea al usuario

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiServiceApi apiInstance = new UsuarioApiServiceApi(defaultClient);
    String nombreUsuario = "nombreUsuario_example"; // String | 
    String password = "password_example"; // String | 
    try {
      Usuario result = apiInstance.login(nombreUsuario, password);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiServiceApi#login");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nombreUsuario** | **String**|  | |
| **password** | **String**|  | |

### Return type

[**Usuario**](Usuario.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

