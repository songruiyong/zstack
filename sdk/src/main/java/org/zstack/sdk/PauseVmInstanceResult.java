package org.zstack.sdk;

public class PauseVmInstanceResult {
    public VmInstanceInventory inventory;
    public void setInventory(VmInstanceInventory inventory) {
        this.inventory = inventory;
    }
    public VmInstanceInventory getInventory() {
        return this.inventory;
    }

}
