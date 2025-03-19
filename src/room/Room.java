package room;

import enum_template.EnumTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public enum Room implements EnumTemplate{
    LIVING_ROOM("Living Room"),
    KITCHEN("Kitchen"),
    BEDROOM("Bedroom"),
    BATHROOM("Bathroom"),
    DINING_ROOM("Dining Room"),
    OFFICE("Office"),
    GUEST_ROOM("Guest Room"),
    LAUNDRY_ROOM("Laundry Room"),
    HALLWAY("Hallway"),
    BASEMENT("Basement"),
    ATTIC("Attic"),
    GARAGE("Garage");

    private final String caption;
    private static final Map<String, Room> LOOKUP_MAP = new HashMap<>();

    static {
        for (Room room : Room.values()) {
            LOOKUP_MAP.put(room.caption, room);
        }
    }

    Room(String caption) {
        this.caption = caption;
    }
    @Override
    public String getCaption() {
        return this.caption;
    }
    public static Set<String> getAllEnumCaptions() {
        return EnumTemplate.getEnumCaptions(LOOKUP_MAP);
    }
    public static Room getEnumByCaption(String caption) {
        return EnumTemplate.getEnumByCaption(LOOKUP_MAP, caption);
    }


    public static void main(String[] args) {
        System.out.println(Room.getAllEnumCaptions());
        Room room = Room.getEnumByCaption("Basement");
        System.out.println(room.caption);
    }
}
