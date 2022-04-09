
public class Photo {
    
    private int photo_id;    //primary key
    private int pub_id;      //foreign key
    private java.util.Date date;
    private String reference;
    private String location;
    private String caption;

    public int getPhotoId(){
        return photo_id;
    }
    public void setPhotoId(int photo_id){
        this.photo_id = photo_id;
    }

    public int getPubId(){
        return pub_id;
    }
    public void setPubId(int pub_id){
        this.pub_id = pub_id;
    }

    public java.util.Date getDate() {
        return date;
    }
    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }

}
