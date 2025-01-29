import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Esta classe armazena informações sobre o jogador, como seu nome, sua idade e 
 * em qual sala ele está atualmente.
 * 
 * @author  Fernanda Carvalho e Marcella Del Rio
 * @version 27/01/2025 :D
 */
public class Player
{
    private String currentRoom;
    private String playerName;
    private int playerAge;
    private float maxWeight;
    private float totalWeight;
    private Map<String, RoomItems> playerItems;

    /**
     * Cria o jogador a partir de suas informações iniciais.
     */
    public Player(String room, String name, int age) {
        currentRoom = room;
        playerName = name;
        playerAge = age;
        playerItems = new HashMap<>();
        maxWeight = 10;
    }

    /**
     * Retorna informações sobre o jogador.
     */
    public String getStringPlayer() {
        String playerInfo = "Seu nome é: " + playerName + " você tem " + playerAge + "e você está" + currentRoom;
        return playerInfo;
    }
    
    /**
     * Retorna localização do jogador.
     */
    public String getPlayerCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Atualiza localização do jogador.
     */
    public void setPlayerCurrentRoom(String room) {
        currentRoom = room;
    }
    
    /**
     * Atualiza peso máximo de carga do jogador.
     */
    public void setMaxWeight(float weight) {
        maxWeight = weight;
    }
    
    /**
     * Determina os itens que o personagem pegou na sala.
     */
    public void addItem(String itemName, RoomItems roomItem) {
        playerItems.put(itemName, roomItem);
        totalWeight += roomItem.getItemWeight();
    }
    
    /**
     * Retorna total de peso com o jogador.
     */
    public float getTotalWight() {
        return totalWeight;
    }

    /**
     * Retorna peso máximo que o jogador pode carregar.
     */
    public float getMaxWight() {
        return maxWeight;
    }
    
    /**import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Esta classe armazena informações sobre o jogador, como seu nome, sua idade e 
 * em qual sala ele está atualmente.
 * 
 * @author  Fernanda Carvalho e Marcella Del Rio
 * @version 27/01/2025 :D
 */
public class Player
{
    private String currentRoom;
    private String playerName;
    private int playerAge;
    private float maxWeight;
    private float totalWeight;
    private Map<String, RoomItems> playerItems;

    /**
     * Cria o jogador a partir de suas informações iniciais.
     */
    public Player(String room, String name, int age) {
        currentRoom = room;
        playerName = name;
        playerAge = age;
        playerItems = new HashMap<>();
        maxWeight = 10;
    }

    /**
     * Retorna informações sobre o jogador.
     */
    public String getStringPlayer() {
        String playerInfo = "Seu nome é: " + playerName + " você tem " + playerAge + "e você está" + currentRoom;
        return playerInfo;
    }
    
    /**
     * Retorna localização do jogador.
     */
    public String getPlayerCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Atualiza localização do jogador.
     */
    public void setPlayerCurrentRoom(String room) {
        currentRoom = room;
    }
    
    /**
     * Atualiza peso máximo de carga do jogador.
     */
    public void setMaxWeight(float weight) {
        maxWeight = weight;
    }
    
    /**
     * Atualiza peso total.
     */
    public void setTotalWeight(RoomItems roomItem, String command) {
        if(command.equals("take")) {
            totalWeight += roomItem.getItemWeight();
        } else {
            totalWeight -= roomItem.getItemWeight();
        }
    }
    
    /**
     * Determina os itens que o personagem pegou na sala.
     */
    public void addItem(String itemName, RoomItems roomItem) {
        playerItems.put(itemName, roomItem);
        setTotalWeight(roomItem, "take");
    }
    
    /**
     * Retorna total de peso com o jogador.
     */
    public float getTotalWight() {
        return totalWeight;
    }

    /**
     * Retorna peso máximo que o jogador pode carregar.
     */
    public float getMaxWight() {
        return maxWeight;
    }
    
    /**
     * Retorna string com itens do jogador.
     */
    public String items() {
        String itemString = "Você tem o seguintes itens: ";
        Set<String> keys = playerItems.keySet();
        for(String item : keys) {
            itemString += " " + item;
        }
        
        itemString += ". Esses itens pesam: " + totalWeight;
        
        return itemString;
    } 
    
    /**
     * Remove um item armazenado com o jogador.
     */
    public void removeItem(String itemName) {
        RoomItems roomItem = playerItems.get(itemName);
        setTotalWeight(roomItem, "drop");
        playerItems.remove(itemName);
    }
    
    /**
     * Retorna informações do item selecionado do jogador.
     */
    public RoomItems getPlayerItem(String ItemName) {
        return playerItems.get(ItemName);
    }
}

     * Retorna string com itens do jogador.
     */
    public String items() {
        String itemString = "Você tem o seguintes itens: ";
        Set<String> keys = playerItems.keySet();
        for(String item : keys) {
            itemString += " " + item;
        }
        
        itemString += ". Esses itens pesam: " + totalWeight;
        
        return itemString;
    } 
    
    /**
     * Remove um item armazenado com o jogador.
     */
    public void removeItem(String itemName) {
        RoomItems roomItem = playerItems.get(itemName);
        totalWeight -= roomItem.getItemWeight();
        playerItems.remove(itemName);
    } 
}
