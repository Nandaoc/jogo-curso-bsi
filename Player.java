
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

    /**
     * Cria o jogador a partir de suas informações iniciais.
     */
    public Player(String room, String name, int age) {
        currentRoom = room;
        playerName = name;
        playerAge = age;
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
}
