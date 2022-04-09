public class Publication {
    
//SUMMARY
//Publication object are used to store the publication table records

    private int publi_id;   //primary key
    private String shortcode;
    private String name;

    public int getPubli_id() {
        return publi_id;
    }
    public void setPubli_id(int publi_id) {
        this.publi_id = publi_id;
    }


    public String getShortcode() {
        return shortcode;
    }
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
