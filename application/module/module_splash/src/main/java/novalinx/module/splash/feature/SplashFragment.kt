package novalinx.module.splash.feature

import novalinx.core.lib.base.app.BaseAppFragment
import novalinx.module.splash.BR
import novalinx.module.splash.R
import novalinx.module.splash.SplashViewModel
import novalinx.module.splash.databinding.SplashFragmentBinding

class SplashFragment : BaseAppFragment<SplashFragmentBinding, SplashViewModel>(
    R.layout.splash_fragment, BR.viewModel
)