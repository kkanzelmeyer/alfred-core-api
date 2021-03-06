package com.alfred.common.datamodel;

import org.json.JSONObject;

import com.alfred.common.messages.StateDeviceProtos.StateDeviceMessage;
import com.alfred.common.messages.StateDeviceProtos.StateDeviceMessage.State;
import com.alfred.common.messages.StateDeviceProtos.StateDeviceMessage.Type;

/**
 * 
 * This class is used to represent a device connected to Alfred.
 * <p>
 * The term "State Device" is used because the class is instantiated for
 * electrical devices (Light, Ceiling Fan, Garage Door, Doorbell) that can be
 * represented by a state (On, Off, Active, Inactive, Open, Closed, etc).
 * <p>
 * For example, the <a
 * href="https://github.com/kkanzelmeyer/alfred-server">Alfred Server</a>
 * project for Raspberry Pi can have sensors and switches connected to it. You
 * could connect a relay to a lamp and to the Raspberry Pi. Using the Alfred
 * Server project you could create a StateDevice to represent the lamp, and then
 * let the Pi control it using some control method.
 * <p>
 * It is worth noting that the <a
 * href="https://github.com/kkanzelmeyer/alfred-server">Alfred Server</a>
 * project for Raspberry Pi has pre-made plugins to handle devices like lamps,
 * lights ceiling fans, garage doors, and doorbells. If you have a Pi you can
 * download the source code and start using it without changing any source code.
 * There is also an <a
 * href="https://github.com/kkanzelmeyer/alfred-client">Alfred Client</a>
 * project for Android with gives the user control over devices connected to the
 * Pi using an Android device.
 * 
 * @author Kevin Kanzelmeyer
 *
 */
public class StateDevice {

    private String _id;
    private String _name;
    private State  _state;
    private Type   _type;

    /**
     * Builder constructor
     * 
     * @param builder The builder constructor
     */
    public StateDevice(Builder builder) {
        _id    = builder.getId();
        _name  = builder.getName();
        _state = builder.getState();
        _type  = builder.getType();
    }
    
    /**
     * Copy constructor
     * 
     * Creates a copy from a reference
     * 
     */
    public StateDevice(StateDevice device) {
        _id    = device.getId();
        _name  = device.getName();
        _state = device.getState();
        _type  = device.getType();
    }
    
    /**
     * This constructor takes a state device protobuf message
     * 
     * @param msg a StateDeviceMessage protobuf
     */
    public StateDevice(StateDeviceMessage msg) {
        _id = msg.getId();
        _state = msg.getState();
        if(msg.hasName()) _name = msg.getName();
        if(msg.hasType()) _type = msg.getType();
    }
    
    /**
     * Constructor for a JSON Object
     * 
     * @param obj
     *            JSON Object representation of a state device
     * @throws Exception
     *             IllegalArgumentException if the JSON State Device input
     *             doesn't have an "id" field
     */
    public StateDevice(JSONObject obj) throws Exception {
        if((!obj.has("id")) ||
           (!obj.has("name")) ||
           (!obj.has("state")) ||
           (!obj.has("type"))) throw new IllegalArgumentException("Error: missing field \"id\"");
        _id = obj.getString("id");
        _name = obj.getString("name");
        
        // type
        if(obj.get("type").equals("doorbell")) _type = Type.DOORBELL;
        else if(obj.get("type").equals("garagedoor")) _type = Type.GARAGEDOOR;
        else if(obj.get("type").equals("light")) _type = Type.LIGHT;
        else if(obj.get("type").equals("ceilingfan")) _type = Type.CEILINGFAN;
        else throw new IllegalArgumentException("Error: unknown type");
        
        // state
        if(obj.get("state").equals("on")) _state = State.ON;
        else if(obj.get("state").equals("off")) _state = State.OFF;
        else if(obj.get("state").equals("active")) _state = State.ACTIVE;
        else if(obj.get("state").equals("inactive")) _state = State.INACTIVE;
        else if(obj.get("state").equals("open")) _state = State.OPEN;
        else if(obj.get("state").equals("closed")) _state = State.CLOSED;
        else throw new IllegalArgumentException("Error: unknown state");
    }
    

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public State getState() {
        return _state;
    }

    public void setState(State state) {
        this._state = state;
    }
    
    public Type getType() {
        return this._type;
    }
    
    @Override
    public String toString() {
        return    "\nDevice ID: " + _id
                + "\nDevice Name: " + _name
                + "\nDevice Type: " + _type
                + "\nDevice State: " + _state;
        
    }
    
    /**
     * Creates a StateDeviceMessage object from the state device instance
     * 
     * @return A StateDeviceMessage object
     */
    public StateDeviceMessage toMessage() {
        return StateDeviceMessage.newBuilder()
                .setId(_id)
                .setName(_name)
                .setState(_state)
                .setType(_type)
                .build();
    }

    
    /**
     * Builder for the StateDevice. This is the only way to create a StateDevice
     * instance
     * 
     * @author Kevin Kanzelmeyer
     *
     */
    public static class Builder {
        
        private String id;
        private String name;
        private State state;
        private Type type;
        
        public String getId() {
            return id;
        }
        
        public Builder setId(String id) {
            this.id = id;
            return this;
        }
        public String getName() {
            return name;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public State getState() {
            return state;
        }
        
        public Builder setState(State state) {
            this.state = state;
            return this;
        }
        
        public Type getType() {
            return type;
        }
        
        public Builder setType(Type type) {
            this.type = type;
            return this;
        }
        
        public StateDevice build() {
            return new StateDevice(this);
        }
    }
    
}
