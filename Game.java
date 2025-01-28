import java.util.Stack;

/**
 *  Esta é a classe principal do jogo "Curso de BSI". 
 *  "Curso de BSI" é um jogo simples de aventura baseado em texto. Os jogadores 
 *  caminharão pelo cenários da faculdae com o objetivo de sair dela por meio
 *  da resolução de enigmas sobre os conteúdos do curso.
 * 
 *  Para jogar, crie uma instânncia desta classe e execue o método "play",
 * 
 *  Esta classe cria e inicializa todas as demais: cria todas as salas, os
 *  analisadores e inicializa o jogo. Ela também avalia e executa os 
 *  comandos retornados pelo analisador.
 * 
 * @author  Fernanda Carvalho e Marcella Del Rio
 * @version 26/01/2025 :D
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private Player player;
         
    /**
     * Cria o jogo e inicializa seu mapa.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previousRooms = new Stack<>();
    }

    /**
     * Cria e conceta todas as salas.
     */
    private void createRooms()
    {
        Room outside, winner, courtyard, cafe, library, livingArea, corridor1, corridor2, office, acr, lab1, lab2, lab3, corridor3, classroom, parking, sportsCourt;
      
        // cria as salas
        outside = new Room("lado de fora do campus");
        courtyard = new Room("no pátio da faculdade");
        cafe = new Room("na lanchonete");
        lab1 = new Room("no lab 1");
        library = new Room("na biblioteca");
        livingArea = new Room("na área de convivência");
        corridor1 = new Room("no corredor 1");
        corridor2 = new Room("no corredor 2");
        office = new Room("no gabinete");
        acr = new Room("no CRA");
        lab2 = new Room("no lab2");
        lab3 = new Room("no lab3");
        corridor3 = new Room("no corredor 3");
        classroom = new Room("na sala de aula");
        parking = new Room("no estacionamento");
        sportsCourt = new Room("na quadra de esportes");
        winner = new Room("VENCEU!");
        
        // inicializa as saídas
        outside.setExit("frente", courtyard);
        courtyard.setExit("frente", corridor1);
        courtyard.setExit("esquerda", cafe);
        courtyard.setExit("trás", outside);
        cafe.setExit("frente", lab1);
        cafe.setExit("trás", courtyard);
        lab1.setExit("trás", cafe);
        lab1.setExit("frente", library);
        lab1.setExit("esquerda", outside);
        lab1.setExit("direita", livingArea);
        library.setExit("trás", lab1);
        library.setExit("direita", livingArea);
        livingArea.setExit("esquerda", sportsCourt);
        livingArea.setExit("frente", classroom);
        livingArea.setExit("direita", corridor1);
        livingArea.setExit("trás", library);
        corridor1.setExit("frente", corridor2);
        corridor1.setExit("trás", courtyard);
        corridor1.setExit("direita", parking);
        corridor1.setExit("esquerda", livingArea);
        corridor2.setExit("trás", corridor1);
        corridor2.setExit("frente", office);
        corridor2.setExit("esquerda", corridor3);
        corridor3.setExit("trás", corridor2);
        corridor3.setExit("direita1", acr);
        corridor3.setExit("direita2", lab1);
        corridor3.setExit("direita3", lab3);
        office.setExit("trás", corridor2);
        acr.setExit("trás", corridor3);
        lab2.setExit("trás", corridor3);
        lab3.setExit("trás", corridor3);
        classroom.setExit("trás", livingArea);
        parking.setExit("trás", corridor1);
        sportsCourt.setExit("trás", livingArea);
        
        // inicializa itens da sala
        courtyard.addItem("cadeira 1", 2);
        courtyard.addItem("cadeira 2", 2);
        courtyard.addItem("mesa", 4);
        library.addItem("livro 1", 0.5f);
        cafe.addItem("café", 0.01f);
        livingArea.addItem("cogumelo", 0.01f);
        courtyard.addItem("chave 1", 0);
        library.addItem("chave 2", 0);
        corridor1.addItem("chave 3", 0);
        livingArea.addItem("chave 4", 0);
        classroom.addItem("chave 5", 0);
        acr.addItem("chave 6", 0);
        corridor3.addItem("chave 7", 0);
        lab3.addItem("chave 8", 0);
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play(String name, int age) 
    {            
        printWelcome();
        createPlayer(currentRoom.getDescription(), name,age);
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigada por jogar! Até mais!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Boas-vindas ao Curso de BSI!");
        System.out.println("O Curso de BSI é uma aventura longa e cansativa, mas é legal.");
        System.out.println("Digite 'help' se você precisar de ajuda.");
        System.out.println("Crie um personagem para iniciar sua aventura.");
        System.out.println();
        pritnLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Eu não consigo compilar isto...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("eat") && !command.hasSecondWord()) {
            eat();
        } else if (commandWord.equals("back")) {
            back(command);
        } else if (commandWord.equals("take")) {
            take(command);
        } else if (commandWord.equals("drop")) {
            drop(command);
        } else if (commandWord.equals("show")) {
            show();
        } else if (commandWord.equals("eat") && command.hasSecondWord()) {
            eatMushroom(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdidido(a). você está só. Você vagueia");
        System.out.println("ao redor da faculdade.");
        System.out.println();
        System.out.println("Seus comandos possíveis são:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Vai aonde?");
            return;
        }
        
        previousRooms.push(currentRoom);
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("frente")) {
            nextRoom = currentRoom.getExit("frente");
        }
        if(direction.equals("trás")) {
            nextRoom = currentRoom.getExit("trás");
        }
        if(direction.equals("direita")) {
            nextRoom = currentRoom.getExit("direita");
        }
        if(direction.equals("esquerda")) {
            nextRoom = currentRoom.getExit("esquerda");
        }
        if(direction.equals("direita1")) {
            nextRoom = currentRoom.getExit("direita1");
        }
        if(direction.equals("direita2")) {
            nextRoom = currentRoom.getExit("direita2");
        }
        if(direction.equals("direita3")) {
            nextRoom = currentRoom.getExit("direita3");
        }

        if (nextRoom == null) {
            System.out.println("Não existe saída por aqui!");
        }
        else {
            currentRoom = nextRoom;
            pritnLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Desistir agora?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Imprime a localização atual do jogador.
    */
    private void pritnLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.getItemString());
    }
    
    /**
     * Repete a localização atual do jogador.
    */
    private void look() {
        System.out.println(currentRoom.getDescription());
    }
    
    /**
     * Permite que o jogador coma ou não.
    */
    private void eat() {
        if(currentRoom.getDescription().equals("na lanchonete")) {
            System.out.println("Você deveria tomar um café e comer um pão de queijo!");
        } else {
            System.out.println("Você já comeu e não está com fome mais.");
        }
    }
    
    /**
     * Permite que o jogador volte ao local de onde veio.
    */
    private void back(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Você pode apenas voltar de onde veio.");
            return;
        }
        
        if(previousRooms.isEmpty()) {
            System.out.println("Você já voltou ao início, daqui pra trás é só pra frente.");
        } else {
            currentRoom = previousRooms.pop();
            pritnLocationInfo();
        }
    }

    /**
     * Permite criar um personagem com informações passadas pelo usuário.
    */
    private Player createPlayer(String room, String name, int age) {
        player = new Player(room, name, age);
        
        return player;
    }
    
    /**
     * Permite que o jogador pegue um item.
    */
    private void take(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("O que você quer pegar?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        if(player.getTotalWight() <= player.getMaxWight()) {
            player.addItem(itemName, currentRoom.getRoomItem(itemName));
        } else {
            System.out.println("Você chegou ao limite de peso que consegue carregar.");
        }
        
        show();
    }
    
    /**
     * Imprime os itens que o jogador possui.
    */
    private void show() {
        System.out.println(player.items());
        System.out.println("Você deseja descartar algo?");
    }
    
    /**
     * Permite que o jogador descarte um item.
    */
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("O que você quer descartar?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        player.removeItem(itemName);
        
        show();
    }
    
    private void eatMushroom(Command command) {
        String food = command.getSecondWord();
        System.out.println(food);
        if(food.equals("cogumelo")) {
            player.setMaxWeight(20);
            
            System.out.println("Parabéns! Vcoê ganhou um aumento no peso que pode carregar.");
            System.out.println();
            System.out.println("Você pode carregar um total de 20.");
            
            currentRoom.removeItem("cogumelo");
        } else {
            System.out.println("Não existe este alimento aqui.");
        }
    }
}
