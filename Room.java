import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * A classe "Room" define um local no cenário do jogo com suas características, 
 * como nome, saídas e itens que contém na sala.
 * 
 * @author  Fernanda Carvalho e Marcella Del Rio
 * @version 26/01/2025 :D
 */
public class Room 
{
    private String description;
    private Map<String, Room> exits;
    private Map<String, RoomItems> roomItems;

    /**
     * Cria a descrição da sala e inicializa detalhes dela como saídas e itens 
     * contidos nela.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        roomItems = new HashMap<>();
    }

    /**
     * Determina as saídas da sala.
     */
    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    /**
     * Retorna a descrição da sala.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna a saída da sala.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * Retorna uma string com todas as saídas possíveis da sala.
     */
    public String getExitString() {
        String exitString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            exitString += " " + exit;
        }
        
        return exitString;
    }
    
    /**
     * Retorna uma string com todas as saídas possíveis da sala e a descrição 
     * da sala atual.
     */
    public String getLongDescription() {
        return "Você está " + description + ". \n" + getExitString();
    }
    
    /**
     * Determina os itens da sala.
     */
    public void addItem(String itemName, float ItemWeight) {
        RoomItems roomItem = new RoomItems(itemName, ItemWeight);
        roomItems.put(itemName, roomItem);
    }
    
    /**
     * Retorna informações do item selecionado na sala.
     */
    public RoomItems getRoomItem(String ItemName) {
        return roomItems.get(ItemName);
    }
    
    /**
     * Retorna string com itens da sala.
     */
    public String getItemString() {
        String itemString = "Itens na sala: ";
        Set<String> keys = roomItems.keySet();
        for(String item : keys) {
            itemString += " " + item;
        }
        
        return itemString;
    }
    
    /**
     * Remove um item da sala.
     */
    public void removeItem(String itemName) {
        roomItems.remove(itemName);
    }
}
