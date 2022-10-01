package ram.talia.hexal.common.casting.actions.everbook

import at.petrak.hexcasting.api.spell.ConstManaOperator
import at.petrak.hexcasting.api.spell.SpellDatum
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getChecked
import at.petrak.hexcasting.api.spell.math.HexPattern
import at.petrak.hexcasting.api.spell.mishaps.MishapOthersName
import ram.talia.hexal.xplat.IXplatAbstractions

object OpEverbookWrite : ConstManaOperator {
	override val argc = 2

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val key = args.getChecked<HexPattern>(0, argc)
		val iota = args[1]

		val trueName = MishapOthersName.getTrueNameFromDatum(iota, ctx.caster)
		if (trueName != null)
			throw MishapOthersName(trueName)

		IXplatAbstractions.INSTANCE.setEverbookIota(ctx.caster, key, iota)

		return listOf()
	}
}