package ch.quazz.caverna.score;

import java.util.ArrayList;

import ch.quazz.caverna.R;

public class Item {
    private int titleID;
    private int descriptionID;
    private int imageID;

    public Item(int titleID, int descriptionID, int imageID) {
        this.titleID = titleID;
        this.descriptionID = descriptionID;
        this.imageID = imageID;
    }

    public int getTitleID() {
        return titleID;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    public int getImageID() {
        return imageID;
    }

    public static ArrayList<Item> getWealthItems() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new TokenItem(R.string.dogs,           R.string.dogs_description, Token.Dogs,          R.drawable.dog,             R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.sheep,          R.string.dogs_description, Token.Sheep,         R.drawable.sheep,           R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.donkeys,        R.string.dogs_description, Token.Donkeys,       R.drawable.donkey,          R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.boars,          R.string.dogs_description, Token.Boars,         R.drawable.boar,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.cattle,         R.string.dogs_description, Token.Cattle,        R.drawable.cattle,          R.integer.wealth_min, R.integer.wealth_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TokenItem(R.string.grain,          R.string.dogs_description, Token.Grains,        R.drawable.grain,           R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.vegetable,      R.string.dogs_description, Token.Vegetables,    R.drawable.vegetable,       R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.rubies,         R.string.dogs_description, Token.Rubies,        R.drawable.ruby,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.gold,           R.string.dogs_description, Token.Gold,          R.drawable.gold,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.begging_markers,R.string.dogs_description, Token.BeggingMarkers,R.drawable.begging_marker,  R.integer.wealth_min, R.integer.wealth_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TokenItem(R.string.small_pastures, R.string.dogs_description, Token.SmallPastures, R.drawable.small_pasture,   R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.large_pastures, R.string.dogs_description, Token.LargePastures, R.drawable.large_pasture,   R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.ore_mines,      R.string.dogs_description, Token.OreMines,      R.drawable.ore_mine,        R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.ruby_mines,     R.string.dogs_description, Token.RubyMines,     R.drawable.ruby_mine,       R.integer.wealth_min, R.integer.wealth_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TokenItem(R.string.unused_tiles,   R.string.dogs_description, Token.UnusedSpace,   R.drawable.unused,          R.integer.wealth_min, R.integer.wealth_max));
        return list;
    }

    public static ArrayList<Item> getFamilyItems() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new TokenItem(R.string.dwarfs,             R.string.dogs_description,     Token.Dwarfs,            R.drawable.dwarf,    R.integer.dwarfs_min,    R.integer.dwarfs_max));
        list.add(new TokenItem(R.string.weapons,            R.string.dogs_description,     Token.Weapons,           R.drawable.weapon,   R.integer.weapons_min,   R.integer.weapons_max));
        list.add(new TokenItem(R.string.dwellings,          R.string.dogs_description,     Token.Dwellings,         R.drawable.dwelling, R.integer.dwellings_min, R.integer.dwellings_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.simple_dwelling,     R.string.dwelling_description, Tile.SimpleDwelling_4_2, R.drawable.simple_dwelling_1,   null));
        list.add(new TileItem(R.string.simple_dwelling,     R.string.dwelling_description, Tile.SimpleDwelling_3_3, R.drawable.simple_dwelling_2,   null));
        list.add(new TileItem(R.string.mixed_dwelling,      R.string.dwelling_description, Tile.MixedDwelling,      R.drawable.mixed_dwelling,      null));
        list.add(new TileItem(R.string.couple_dwelling,     R.string.dwelling_description, Tile.CoupleDwelling,     R.drawable.couple_dwelling,     null));
        list.add(new TileItem(R.string.additional_dwelling, R.string.dwelling_description, Tile.AdditionalDwelling, R.drawable.additional_dwelling, null));
        return list;
    }

    public static ArrayList<Item> getCaveItems() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new TileItem(R.string.carpenter, R.string.dwelling_description, Tile.Carpenter, R.drawable.carpenter, null));
        list.add(new TileItem(R.string.stone_carver, R.string.dwelling_description, Tile.StoneCarver, R.drawable.stone_carver, null));
        list.add(new TileItem(R.string.blacksmith, R.string.dwelling_description, Tile.Blacksmith, R.drawable.blacksmith, null));
        list.add(new TileItem(R.string.miner, R.string.dwelling_description, Tile.Miner, R.drawable.miner, null));
        list.add(new TileItem(R.string.builder, R.string.dwelling_description, Tile.Builder, R.drawable.builder, null));
        list.add(new TileItem(R.string.trader, R.string.dwelling_description, Tile.Trader, R.drawable.trader, null));
        list.add(new TileItem(R.string.slaughtering_cave, R.string.dwelling_description, Tile.SlaughteringCave, R.drawable.slaughtering_cave, null));
        list.add(new TileItem(R.string.cooking_cave, R.string.dwelling_description, Tile.CookingCave, R.drawable.cooking_cave, null));
        list.add(new TileItem(R.string.working_cave, R.string.dwelling_description, Tile.WorkingCave, R.drawable.working_cave, null));
        list.add(new TileItem(R.string.mining_cave, R.string.dwelling_description, Tile.MiningCave, R.drawable.mining_cave, null));
        list.add(new TileItem(R.string.breeding_cave, R.string.dwelling_description, Tile.BreedingCave, R.drawable.breeding_cave, null));
        list.add(new TileItem(R.string.peaceful_cave, R.string.dwelling_description, Tile.PeacefulCave, R.drawable.peaceful_cave, null));
        list.add(new TileItem(R.string.cuddle_room, R.string.dwelling_description, Tile.CuddleRoom, R.drawable.cuddle_room, null));
        list.add(new TileItem(R.string.breakfast_room, R.string.dwelling_description, Tile.BreakfastRoom, R.drawable.breakfast_room, null));
        list.add(new TileItem(R.string.stubble_room, R.string.dwelling_description, Tile.StubbleRoom, R.drawable.stubble_room, null));
        list.add(new TileItem(R.string.work_room, R.string.dwelling_description, Tile.WorkRoom, R.drawable.work_room, null));
        list.add(new TileItem(R.string.guest_room, R.string.dwelling_description, Tile.GuestRoom, R.drawable.guest_room, null));
        list.add(new TileItem(R.string.office_room, R.string.dwelling_description, Tile.OfficeRoom, R.drawable.office_room, null));
        list.add(new TileItem(R.string.wood_supplier, R.string.dwelling_description, Tile.WoodSupplier, R.drawable.wood_supplier, null));
        list.add(new TileItem(R.string.stone_supplier, R.string.dwelling_description, Tile.StoneSupplier, R.drawable.stone_supplier, null));
        list.add(new TileItem(R.string.ruby_supplier, R.string.dwelling_description, Tile.RubySupplier, R.drawable.ruby_supplier, null));
        list.add(new TileItem(R.string.dog_school, R.string.dwelling_description, Tile.DogSchool, R.drawable.dog_school, null));
        list.add(new TileItem(R.string.quarry, R.string.dwelling_description, Tile.Quarry, R.drawable.quarry, null));
        list.add(new TileItem(R.string.seam, R.string.dwelling_description, Tile.Seam, R.drawable.seam, null));
        return list;
    }

    public static ArrayList<Item> getBonusItems() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new TileItem(R.string.weaving_parlor, R.string.dwelling_description, Tile.WeavingParlor, R.drawable.weaving_parlor, null));
        list.add(new TileItem(R.string.milking_parlor, R.string.dwelling_description, Tile.MilkingParlor, R.drawable.milking_parlor, null));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.state_parlor, R.string.dwelling_description, Tile.StateParlor, R.drawable.state_parlor, Token.AdjacentDwellings));
        list.add(new TokenItem(R.string.adjacent_dwellings, R.string.dogs_description, Token.AdjacentDwellings, R.drawable.adjacent_dwellings, R.integer.adjacent_min, R.integer.adjacent_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.hunting_parlor, R.string.dwelling_description, Tile.HuntingParlor, R.drawable.hunting_parlor, null));
        list.add(new TileItem(R.string.beer_parlor, R.string.dwelling_description, Tile.BeerParlor, R.drawable.beer_parlor, null));
        list.add(new TileItem(R.string.blacksmithing_parlor, R.string.dwelling_description, Tile.BlacksmithingParlor, R.drawable.blacksmithing_parlor, null));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.stone_storage, R.string.dwelling_description, Tile.StoneStorage, R.drawable.stone_storage, Token.Stone));
        list.add(new TokenItem(R.string.stone,R.string.dogs_description, Token.Stone, R.drawable.stone, R.integer.wealth_min, R.integer.wealth_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.ore_storage, R.string.dwelling_description, Tile.OreStorage, R.drawable.ore_storage, Token.Ore));
        list.add(new TokenItem(R.string.ore, R.string.dogs_description, Token.Ore, R.drawable.ore, R.integer.wealth_min, R.integer.wealth_max));
        list.add(new Item(0, 0, 0)); // Separator
        list.add(new TileItem(R.string.spare_part_storage, R.string.dwelling_description, Tile.SparePartStorage, R.drawable.spare_part_storage, null));
        list.add(new TileItem(R.string.main_storage, R.string.dwelling_description, Tile.MainStorage, R.drawable.main_storage, null));
        list.add(new TileItem(R.string.supplies_storage, R.string.dwelling_description, Tile.SuppliesStorage, R.drawable.supplies_storage, null));
        list.add(new TileItem(R.string.weapon_storage, R.string.dwelling_description, Tile.WeaponStorage, R.drawable.weapon_storage, Token.Weapons));
        list.add(new TileItem(R.string.prayer_chamber, R.string.dwelling_description, Tile.PrayerChamber, R.drawable.prayer_chamber, Token.Weapons));
        list.add(new TileItem(R.string.broom_chamber, R.string.dwelling_description, Tile.BroomChamber, R.drawable.broom_chamber, null));
        list.add(new TileItem(R.string.treasure_chamber, R.string.dwelling_description, Tile.TreasureChamber, R.drawable.treasure_chamber, null));
        list.add(new TileItem(R.string.food_chamber, R.string.dwelling_description, Tile.FoodChamber, R.drawable.food_chamber, null));
        list.add(new TileItem(R.string.writing_chamber, R.string.dwelling_description, Tile.WritingChamber, R.drawable.writing_chamber, null));
        list.add(new TileItem(R.string.fodder_chamber, R.string.dwelling_description, Tile.FodderChamber, R.drawable.fodder_chamber, null));
        return list;
    }
}
