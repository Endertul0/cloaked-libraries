package io.github.endertul.cloaked.item.custom;

import io.github.endertul.cloaked.util.MiscUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class QuickCloakerItem extends Item {
    public QuickCloakerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return MiscUtils.cloak(context, true, context.getBlockPos());
    }
}
