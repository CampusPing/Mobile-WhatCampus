package feature.onboarding

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.img_bell
import whatcampus.composeapp.generated.resources.img_bookmark
import whatcampus.composeapp.generated.resources.img_bubble
import whatcampus.composeapp.generated.resources.img_magnifying_glass
import whatcampus.composeapp.generated.resources.onboarding_bookmark_desc
import whatcampus.composeapp.generated.resources.onboarding_chat_desc
import whatcampus.composeapp.generated.resources.onboarding_push_desc
import whatcampus.composeapp.generated.resources.onboarding_search_desc

enum class OnboardingSliderItem(
    val imageRes: DrawableResource,
    val descriptionRes: StringResource,
) {
    PUSH(
        imageRes = Res.drawable.img_bell,
        descriptionRes = Res.string.onboarding_push_desc,
    ),
    SEARCH(
        imageRes = Res.drawable.img_magnifying_glass,
        descriptionRes = Res.string.onboarding_search_desc,
    ),
    BOOKMARK(
        imageRes = Res.drawable.img_bookmark,
        descriptionRes = Res.string.onboarding_bookmark_desc,
    ),
    CHAT(
        imageRes = Res.drawable.img_bubble,
        descriptionRes = Res.string.onboarding_chat_desc,
    )
}
