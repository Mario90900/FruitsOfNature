package com.mario90900.fruitsofnature.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityFON extends TileEntity{
	protected ForgeDirection orientation;
    protected String customName;
    protected String owner;
    
    public TileEntityFON(){
    	orientation = ForgeDirection.SOUTH;
        customName = "";
        owner = "";
    }
    
    public ForgeDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }
    
    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    
    public boolean hasCustomName() {
        return customName != null && customName.length() > 0;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public boolean hasOwner() {
        return owner != null && owner.length() > 0;
    }
}
