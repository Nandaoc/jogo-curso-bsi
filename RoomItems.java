/**
 * Esta classe contém todos os itens que estão em uma sala do jogo.
 * 
 * @author  Fernanda Carvalho e Marcella Del Rio
 * @version 26/01/2025 :D
 */
public class RoomItems
{
    private String itemName;
    private float itemWeight;

    /**
     * Construtor para objetos da classe RoomItems
     */
    public RoomItems(String name, float weight) {
        itemName = name;
        itemWeight = weight;
    }

    /**
     * Retorna nome do item.
     */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * Retorna nome do item.
     */
    public float getItemWeight()
    {
        return itemWeight;
    }
}
