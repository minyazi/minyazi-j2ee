
package com.minyazi.j2ee.test.webservice.client2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.minyazi.test.webservice.client2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddUsers_QNAME = new QName("http://webservice.test.minyazi.com/", "addUsers");
    private final static QName _AddUsers2_QNAME = new QName("http://webservice.test.minyazi.com/", "addUsers2");
    private final static QName _AddUsers2Response_QNAME = new QName("http://webservice.test.minyazi.com/", "addUsers2Response");
    private final static QName _AddUsersResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "addUsersResponse");
    private final static QName _GetUserById_QNAME = new QName("http://webservice.test.minyazi.com/", "getUserById");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "getUserByIdResponse");
    private final static QName _GetUserName_QNAME = new QName("http://webservice.test.minyazi.com/", "getUserName");
    private final static QName _GetUserNameResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "getUserNameResponse");
    private final static QName _GetUsers_QNAME = new QName("http://webservice.test.minyazi.com/", "getUsers");
    private final static QName _GetUsersByName_QNAME = new QName("http://webservice.test.minyazi.com/", "getUsersByName");
    private final static QName _GetUsersByNameResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "getUsersByNameResponse");
    private final static QName _GetUsersResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "getUsersResponse");
    private final static QName _IsExist_QNAME = new QName("http://webservice.test.minyazi.com/", "isExist");
    private final static QName _IsExistResponse_QNAME = new QName("http://webservice.test.minyazi.com/", "isExistResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.minyazi.test.webservice.client2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddUsers }
     * 
     */
    public AddUsers createAddUsers() {
        return new AddUsers();
    }

    /**
     * Create an instance of {@link AddUsers2 }
     * 
     */
    public AddUsers2 createAddUsers2() {
        return new AddUsers2();
    }

    /**
     * Create an instance of {@link AddUsers2Response }
     * 
     */
    public AddUsers2Response createAddUsers2Response() {
        return new AddUsers2Response();
    }

    /**
     * Create an instance of {@link AddUsersResponse }
     * 
     */
    public AddUsersResponse createAddUsersResponse() {
        return new AddUsersResponse();
    }

    /**
     * Create an instance of {@link GetUserById }
     * 
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     * 
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link GetUserName }
     * 
     */
    public GetUserName createGetUserName() {
        return new GetUserName();
    }

    /**
     * Create an instance of {@link GetUserNameResponse }
     * 
     */
    public GetUserNameResponse createGetUserNameResponse() {
        return new GetUserNameResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link GetUsersByName }
     * 
     */
    public GetUsersByName createGetUsersByName() {
        return new GetUsersByName();
    }

    /**
     * Create an instance of {@link GetUsersByNameResponse }
     * 
     */
    public GetUsersByNameResponse createGetUsersByNameResponse() {
        return new GetUsersByNameResponse();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link IsExist }
     * 
     */
    public IsExist createIsExist() {
        return new IsExist();
    }

    /**
     * Create an instance of {@link IsExistResponse }
     * 
     */
    public IsExistResponse createIsExistResponse() {
        return new IsExistResponse();
    }

    /**
     * Create an instance of {@link MapConvertor }
     * 
     */
    public MapConvertor createMapConvertor() {
        return new MapConvertor();
    }

    /**
     * Create an instance of {@link MapEntry }
     * 
     */
    public MapEntry createMapEntry() {
        return new MapEntry();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "addUsers")
    public JAXBElement<AddUsers> createAddUsers(AddUsers value) {
        return new JAXBElement<AddUsers>(_AddUsers_QNAME, AddUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUsers2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "addUsers2")
    public JAXBElement<AddUsers2> createAddUsers2(AddUsers2 value) {
        return new JAXBElement<AddUsers2>(_AddUsers2_QNAME, AddUsers2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUsers2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "addUsers2Response")
    public JAXBElement<AddUsers2Response> createAddUsers2Response(AddUsers2Response value) {
        return new JAXBElement<AddUsers2Response>(_AddUsers2Response_QNAME, AddUsers2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "addUsersResponse")
    public JAXBElement<AddUsersResponse> createAddUsersResponse(AddUsersResponse value) {
        return new JAXBElement<AddUsersResponse>(_AddUsersResponse_QNAME, AddUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUserName")
    public JAXBElement<GetUserName> createGetUserName(GetUserName value) {
        return new JAXBElement<GetUserName>(_GetUserName_QNAME, GetUserName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUserNameResponse")
    public JAXBElement<GetUserNameResponse> createGetUserNameResponse(GetUserNameResponse value) {
        return new JAXBElement<GetUserNameResponse>(_GetUserNameResponse_QNAME, GetUserNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUsersByName")
    public JAXBElement<GetUsersByName> createGetUsersByName(GetUsersByName value) {
        return new JAXBElement<GetUsersByName>(_GetUsersByName_QNAME, GetUsersByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUsersByNameResponse")
    public JAXBElement<GetUsersByNameResponse> createGetUsersByNameResponse(GetUsersByNameResponse value) {
        return new JAXBElement<GetUsersByNameResponse>(_GetUsersByNameResponse_QNAME, GetUsersByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "isExist")
    public JAXBElement<IsExist> createIsExist(IsExist value) {
        return new JAXBElement<IsExist>(_IsExist_QNAME, IsExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.test.minyazi.com/", name = "isExistResponse")
    public JAXBElement<IsExistResponse> createIsExistResponse(IsExistResponse value) {
        return new JAXBElement<IsExistResponse>(_IsExistResponse_QNAME, IsExistResponse.class, null, value);
    }

}
