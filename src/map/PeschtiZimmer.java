  package map;

import event_system.GameEvent;
import event_system.SetVariableEvent;
import event_system.WaitEvent;
import event_system.control_flow.CasesEvent;
import event_system.control_flow.EventList;
import event_system.dialog.Choicebox;
import event_system.dialog.Textbox;
import event_system.map_control.Teleport;
import event_system.map_control.TurnToPlayer;
import event_system.map_control.WaitForMapEntity;
import event_system.map_entities.MapEntity;
import event_system.map_entities.RandomWalkNPC;
import game.Game;
import util.Direction;
import util.Pair;

import java.io.File;

public class PeschtiZimmer extends game.Map {
    public PeschtiZimmer(){
        super(new File("res/maps/map_peschti.txt"));
        tileset = Game.tilesets.get("outi_room");
        
        mapEntities.add(new MapEntity("teleport1", 9, 13, Direction.DOWN, null, true, null,
                new EventList(new Teleport("village", 5, 17, Direction.DOWN), new WaitEvent(10))));
        
        mapEntities.add(new MapEntity("teleport2", 10, 13, Direction.DOWN, null, true, null,
                new EventList(new Teleport("village", 5, 17, Direction.DOWN), new WaitEvent(10))));
        
        MapEntity paula = new RandomWalkNPC("paula", 11, 4, Direction.DOWN, "paula");
        GameEvent stage0 = new EventList(new TurnToPlayer("paula"),
                new Textbox("Paula: Hallo Peschti!\n" + "Schön, dass du zuhause bist!"),
                new Choicebox("Paula: Hast du Lust auf Pizza?",
                        new EventList(new Textbox("Großartig! Der Business verkauft Pizza!"),
                                new Textbox("Paula gibt Peschti 10P$.") , new SetVariableEvent("PIZZA", 1)),
                        new Textbox("Paula: Ok.")));
        GameEvent stage1 = new EventList(new TurnToPlayer("paula"),
                new Textbox("Paula: Willkommen zuhause!\n" + "Hier kannst du deine Ausrüstung lagern!"));
        GameEvent stage2 = new EventList(new TurnToPlayer("paula"), new Textbox("Peschti gibt Paula die Pizza"),
                new Textbox("Paula: Ihhhh Oliven!"), new SetVariableEvent("PIZZA", 3));
        GameEvent stage3 = new EventList(new TurnToPlayer("paula"),
                new Textbox("Paula: Leider ist diese Pizza ungenießbar!\n" + "Wir müssen uns etwas einfallen lassen!"));
       paula.actionEvent = new EventList(new WaitForMapEntity("paula"),
                new CasesEvent("PIZZA", stage0, new Pair<>(1, stage1), new Pair<>(2, stage2), new Pair<>(3, stage3)));
        mapEntities.add(paula);        
    }
}
