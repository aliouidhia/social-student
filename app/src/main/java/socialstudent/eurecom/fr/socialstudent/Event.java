package socialstudent.eurecom.fr.socialstudent;

public class Event {
    private String name ;
    private String time ;
    private String place ;
    private String description ;

    public Event () {

    }

    public Event (String name,String time,String place,String description){
        this.name=name ;
        this.time=time;
        this.place=place;
        this.description=description;
    }
}
