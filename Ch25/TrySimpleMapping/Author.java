public class Author {
  public Author(int authid, String lastname, String firstname,
                String address[], String city, String state,
                String postcode, String country, 
                String phone, String fax, String email)
  {
    this.authid = authid;
    this.lastname = lastname;
    this.firstname = firstname;
    this.address = address;
    this.city = city;
    this.state = state;
    this.postcode = postcode;
    this.country = country;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
  }

  public int getId() {
    return authid;
  }

  public String getLastName() {
    return lastname;
  }

  public String getFirstName() {
    return firstname;
  }

  public String[] getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostCode() {
    return postcode;
  }

  public String getPhone() {
    return phone;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String toString() {
    return new String
          ("author ID: " + Integer.toString(authid) +
           "\nname     : " + lastname + "," + firstname +
           "\naddress  : " + address[0] +
           "\n         : " + address[1] +
           "\n         : " + city + " " + state +
           "\n         : " + postcode + " " + country +
           "\nphone    : " + phone +
           "\nfax      : " + fax +
           "\nemail    : " + email);
  }

  int authid;
  String lastname;
  String firstname;
  String address[];
  String city;
  String state;
  String postcode;
  String country;
  String phone;
  String fax;
  String email;
}
