package com.chaottic.becquerel.common.component;

import net.minecraft.nbt.CompoundTag;

public final class ChunkGrayComponent implements GrayComponent {
    private double gray;

    @Override
    public void readFromNbt(CompoundTag tag) {
        gray = tag.getDouble("Gray");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putDouble("Gray", gray);
    }

    @Override
    public double getGray() {
        return gray;
    }

    @Override
    public void setGray(double gray) {
        this.gray = gray;
    }

    @Override
    public void addGray(double gray) {
        this.gray += gray;
    }
}
