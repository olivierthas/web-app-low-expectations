﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Magazijns {
    public class MagazijnViewModel : BaseViewModel {
        public IEnumerable<Magazijn> Magazijnen { get; set; }
    }
}
