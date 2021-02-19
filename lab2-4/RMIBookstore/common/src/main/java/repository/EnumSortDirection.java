package repository;

public enum EnumSortDirection
{
    ASC(false),
    DESC(true)
    ;

    private final boolean label;

    private EnumSortDirection(boolean lbl)
    {
        this.label = lbl;
    }
    public boolean getValue()
    {
        return label;
    }
}
