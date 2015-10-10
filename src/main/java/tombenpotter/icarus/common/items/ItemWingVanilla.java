package tombenpotter.icarus.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tombenpotter.icarus.api.IcarusConstants;
import tombenpotter.icarus.api.wings.ISpecialWing;
import tombenpotter.icarus.api.wings.Wing;
import tombenpotter.icarus.common.util.IcarusHelper;
import tombenpotter.icarus.common.util.cofh.StringHelper;

import java.util.List;

public class ItemWingVanilla extends ItemWing implements ISpecialWing {

    private static final int flapsToDamage = 10;

    public ItemWingVanilla(ArmorMaterial material, Wing wing) {
        super(material, wing);
    }

    @Override
    public void onWingFlap(ItemStack stack, EntityPlayer player) {
        IcarusHelper.checkNBT(stack);
        if (!player.onGround) {
            stack.stackTagCompound.setInteger(IcarusConstants.NBT_DAMAGE, stack.stackTagCompound.getInteger(IcarusConstants.NBT_DAMAGE) + 1);
        }
    }

    @Override
    public void onWingHover(ItemStack stack, EntityPlayer player) {
    }

    @Override
    public void onWingTick(ItemStack stack, EntityPlayer player) {
        IcarusHelper.checkNBT(stack);
        int nbtDamage = stack.stackTagCompound.getInteger(IcarusConstants.NBT_DAMAGE);
        if (nbtDamage >= flapsToDamage) {
            stack.stackTagCompound.setInteger(IcarusConstants.NBT_DAMAGE, nbtDamage - flapsToDamage);
            stack.damageItem(1, player);
        }
    }

    @Override
    public boolean canWingBeUsed(ItemStack stack, EntityPlayer player) {
        return true;
    }

    @Override
    public List<String> getDisplayString(World clientWorld, EntityPlayer clientPlayer, ItemStack stack) {
        List<String> list = super.getDisplayString(clientWorld, clientPlayer, stack);
        list.add(StringHelper.LIGHT_BLUE + StringHelper.localize("tooltip.icarus.durability") + StringHelper.END + StringHelper.LIGHT_GRAY + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage() + StringHelper.END);
        return list;
    }
}
