package com.apschulewitz.resdb.common.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adrianschulewitz on 15/08/2016.
 */
public enum MaintainableDataObject {

//    private static Logger logger = LoggerFactory.getLogger(MaintainableDataObject.class);

    AddressType("menu.entity.AddressType"),
    Artefact("menu.entity.Artefact"),
    ArtefactAttribute("menu.entity.ArtefactAttribute"),
    ArtefactAttributeType("menu.entity.ArtefactAttributeType"),
    ArtefactGroup("menu.entity.ArtefactGroup"),
    ArtefactImage("menu.entity.ArtefactImage"),
    ArtefactImagePatternSymbol("menu.entityArtefactImagePatternSymbol."),
    ArtefactPlace("menu.entity.ArtefactPlace"),
    ArtefactRestorationWork("menu.entity.ArtefactRestorationWork"),
    ArtefactType("menu.entity.ArtefactType"),
    Bibliography("menu.entity.Bibliography"),
    BibliographyPublication("menu.entity.BibliographyPublication"),
    Calendar("menu.entity.Calendar"),
    CalendarRace("menu.entity.CalendarRace"),
    CalendarType("menu.entity.CalendarType"),
    Country("menu.entity.Country"),
    CountryRegion("menu.entity.CountryRegion"),
    DataClassAssociation("menu.entity.DataClassAssociation"),
    DataClassMethodArguments("menu.entity.DataClassMethodArguments"),
    Deity("menu.entity.Deity"),
    DeityAlias("menu.entity.DeityAlias"),
    DeityReligion("menu.entity.DeityReligion"),
    DeityType("menu.entity.DeityType"),
    Entity("menu.entity.Entity"),
    EntityAddress("menu.entity.EntityAddress"),
    EntityType("menu.entity.EntityType"),
    Event("menu.entity.Event"),
    EventAttribute("menu.entity.EventAttribute"),
    EventAttributeType("menu.entity.EventAttributeType"),
    EventType("menu.entity.EventType"),
    EventTypeGroup("menu.entity.EventTypeGroup"),
    Hierarchy("menu.entity.Hierarchy"),
    HierarchyNode("menu.entity.HierarchyNode"),
    HierarchyType("menu.entity.HierarchyType"),
    Image("menu.entity.Image"),
    ImageCollectionHeader("menu.entity.ImageCollectionHeader"),
    ImageCollectionSequence("menu.entity.ImageCollectionSequence"),
    ImageType("menu.entity.ImageType"),
    Language("menu.entity.Language"),
    LanguageGroup("menu.entity.LanguageGroup"),
    LanguagePatternSymbol("menu.entity.LanguagePatternSymbol"),
    LibraryLocation("menu.entity.LibraryLocation"),
    Material("menu.entity.Material"),
    Measure("menu.entity.Measure"),
    MeasureType("menu.entity.MeasureType"),
    Menu("menu.entity.Menu"),
    MenuItem("menu.entity.MenuItem"),
    MenuOption("menu.entity.MenuOption"),
    OtherAddress("menu.entity.OtherAddress"),
    PatternSymbol("menu.entity.PatternSymbol"),
    Period("menu.entity.Period"),
    Person("menu.entity.Person"),
    PersonAlias("menu.entity.PersonAlias"),
    PersonDefinition("menu.entity.PersonDefinition"),
    PersonRole("menu.entity.PersonRole"),
    PersonType("menu.entity.PersonType"),
    Place("menu.entity.Place"),
    PlaceAlias("menu.entity.PlaceAlias"),
    PostalAddress("menu.entity.PostalAddress"),
    Publication("menu.entity.Publication"),
    PublicationRole("menu.entity.PublicationRole"),
    PublicationType("menu.entity.PublicationType"),
    Race("menu.entity.Race"),
    RaceAlias("menu.entity.RaceAlias"),
    RacePlace("menu.entity.RacePlace"),
    RaceType("menu.entity.RaceType"),
    Reference("menu.entity.Reference"),
    ReferenceType("menu.entity.ReferenceType"),
    Region("menu.entity.Region"),
    Religion("menu.entity.Religion"),
    River("menu.entity.River"),
    RiverAlias("menu.entity.RiverAlias"),
    SequenceNumber("menu.entity.SequenceNumber"),
    Tale("menu.entity.Tale"),
    TaleType("menu.entity.TaleType"),
    Technology("menu.entity.Technology"),
    TechnologyType("menu.entity.TechnologyType"),
    TechnologyTypeGroup("menu.entity.TechnologyTypeGroup"),
    Theory("menu.entity.Theory"),
    Title("menu.entity.Title"),
    UserAccount("menu.entity.UserAccount"),
    UserGroup("menu.entity.UserGroup"),
    UserGroupMembership("menu.entity.UserGroupMembership");

    private String dataObjectIdentifier;

    private MaintainableDataObject(String dataObjectIdentifier) {
        this.dataObjectIdentifier = dataObjectIdentifier;
    }

    public String getDataObjectIdentifier() {
        return dataObjectIdentifier;
    }

    public void setDataObjectIdentifier(String dataObjectIdentifier) {
        this.dataObjectIdentifier = dataObjectIdentifier;
    }

    public static MaintainableDataObject getFor(String dataObjectIdentifier) {

        switch (dataObjectIdentifier) {

            case "menu.entity.AddressType":
                return AddressType;
            case "menu.entity.Artefact":
                return Artefact;
            case "menu.entity.ArtefactAttribute":
                return ArtefactAttribute;
            case "menu.entity.ArtefactAttributeType":
                return ArtefactAttributeType;
            case "menu.entity.ArtefactGroup":
                return ArtefactGroup;
            case "menu.entity.ArtefactImage":
                return ArtefactImage;
            case "menu.entity.ArtefactImagePatternSymbol":
                return ArtefactImagePatternSymbol;
            case "menu.entity.ArtefactPlace":
                return ArtefactPlace;
            case "menu.entity.ArtefactType":
                return ArtefactType;
            case "menu.entity.CalendarType":
                return CalendarType;
            case "menu.entity.DeityType":
                return DeityType;
            case "menu.entity.EntityType":
                return EntityType;
            case "menu.entity.EventType":
                return EventType;
            case "menu.entity.EventAttributeType":
                return EventAttributeType;
            case "menu.entity.HierarchyType":
                return HierarchyType;
            case "menu.entity.MeasureType":
                return MeasureType;
            case "menu.entity.PersonType":
                return PersonType;
            default:
                System.err.println("Unrecognized fully qualified class: " + dataObjectIdentifier);
                return null;
        }
    }

    public static final Set<MaintainableDataObject> VALID_MAINTAINABLE_DATA_OBJECT = new HashSet<>();

    static {
        VALID_MAINTAINABLE_DATA_OBJECT.add(AddressType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Artefact);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactAttribute);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactAttributeType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactGroup);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactImage);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactImagePatternSymbol);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactPlace);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactRestorationWork);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ArtefactType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Bibliography);
        VALID_MAINTAINABLE_DATA_OBJECT.add(BibliographyPublication);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Calendar);
        VALID_MAINTAINABLE_DATA_OBJECT.add(CalendarRace);
        VALID_MAINTAINABLE_DATA_OBJECT.add(CalendarType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Country);
        VALID_MAINTAINABLE_DATA_OBJECT.add(CountryRegion);
        VALID_MAINTAINABLE_DATA_OBJECT.add(DataClassAssociation);
        VALID_MAINTAINABLE_DATA_OBJECT.add(DataClassMethodArguments);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Deity);
        VALID_MAINTAINABLE_DATA_OBJECT.add(DeityAlias);
        VALID_MAINTAINABLE_DATA_OBJECT.add(DeityReligion);
        VALID_MAINTAINABLE_DATA_OBJECT.add(DeityType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Entity);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EntityAddress);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EntityType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Event);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EventAttribute);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EventAttributeType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EventType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(EventTypeGroup);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Hierarchy);
        VALID_MAINTAINABLE_DATA_OBJECT.add(HierarchyNode);
        VALID_MAINTAINABLE_DATA_OBJECT.add(HierarchyType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Image);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ImageCollectionHeader);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ImageCollectionSequence);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ImageType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Language);
        VALID_MAINTAINABLE_DATA_OBJECT.add(LanguageGroup);
        VALID_MAINTAINABLE_DATA_OBJECT.add(LanguagePatternSymbol);
        VALID_MAINTAINABLE_DATA_OBJECT.add(LibraryLocation);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Material);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Measure);
        VALID_MAINTAINABLE_DATA_OBJECT.add(MeasureType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Menu);
        VALID_MAINTAINABLE_DATA_OBJECT.add(MenuItem);
        VALID_MAINTAINABLE_DATA_OBJECT.add(MenuOption);
        VALID_MAINTAINABLE_DATA_OBJECT.add(OtherAddress);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PatternSymbol);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Period);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Person);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PersonAlias);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PersonDefinition);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PersonRole);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Place);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PlaceAlias);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PostalAddress);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Publication);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PublicationRole);
        VALID_MAINTAINABLE_DATA_OBJECT.add(PublicationType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Race);
        VALID_MAINTAINABLE_DATA_OBJECT.add(RaceAlias);
        VALID_MAINTAINABLE_DATA_OBJECT.add(RacePlace);
        VALID_MAINTAINABLE_DATA_OBJECT.add(RaceType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Reference);
        VALID_MAINTAINABLE_DATA_OBJECT.add(ReferenceType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Region);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Religion);
        VALID_MAINTAINABLE_DATA_OBJECT.add(River);
        VALID_MAINTAINABLE_DATA_OBJECT.add(RiverAlias);
        VALID_MAINTAINABLE_DATA_OBJECT.add(SequenceNumber);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Tale);
        VALID_MAINTAINABLE_DATA_OBJECT.add(TaleType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Technology);
        VALID_MAINTAINABLE_DATA_OBJECT.add(TechnologyType);
        VALID_MAINTAINABLE_DATA_OBJECT.add(TechnologyTypeGroup);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Theory);
        VALID_MAINTAINABLE_DATA_OBJECT.add(Title);
        VALID_MAINTAINABLE_DATA_OBJECT.add(UserAccount);
        VALID_MAINTAINABLE_DATA_OBJECT.add(UserGroup);
        VALID_MAINTAINABLE_DATA_OBJECT.add(UserGroupMembership);
    }
}
